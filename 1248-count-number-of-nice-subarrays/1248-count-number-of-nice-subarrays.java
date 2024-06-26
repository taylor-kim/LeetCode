class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return official_prefixsum(nums, k);
    }

    public int official_prefixsum(int[] nums, int k) {
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);

        int ans = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            count += num % 2;

            ans += map.getOrDefault(count - k, 0);

            map.put(count, map.getOrDefault(count, 0) + 1);
        }

        return ans;
    }

    public int mySol(int[] nums, int k) {
        int n = nums.length;

        int leftOuter = 0;
        int rightOuter = 0;
        
        int left = 0;

        int count = 0;

        int ans = 0;

        for (int right = 0; right < n; right++) {
            count += nums[right] % 2;

            if (count < k) continue;

            while (left <= right) {
                if (nums[left] % 2 == 1) break;
                left++;
            }

            // if (left > right) continue;

            while (leftOuter < left && nums[leftOuter] % 2 == 1) {
                leftOuter++;
            }

            rightOuter = right;

            while (rightOuter + 1 < n && nums[rightOuter + 1] % 2 == 0) {
                rightOuter++;
            }

            // rightOuter = Math.min(rightOuter, n - 1);

            int subAns = (left - leftOuter + 1) * (rightOuter - right + 1);

            // System.out.println(String.format("left:%d, leftOuter:%d, rightOuter:%d, right:%d, count:%d, subAns:%d", left, leftOuter, rightOuter, right, count, subAns));

            ans += subAns;
            
            leftOuter = left;
            count -= nums[left++] % 2;
        }

        return ans;
    }
}