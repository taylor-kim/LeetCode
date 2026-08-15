class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        return editorial(nums, k);
    }

    public int editorial(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int breachCount = 0;
        Map<Integer, Integer> map = new HashMap();

        for (int right = 0; right < n; right++) {
            int r = nums[right];
            map.put(r, map.getOrDefault(r, 0) + 1);

            if (map.get(r) == k + 1) {
                breachCount++;
            }

            if (breachCount > 0) {
                int l = nums[left++];

                map.put(l, map.get(l) - 1);

                if (map.get(l) == k) {
                    breachCount--;
                }
            }
        }

        return n - left;
    }

    public int try_editorial(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int breachCount = 0;
        Map<Integer, Integer> map = new HashMap();
        int windowSize = 0;

        int ans = 0;

        for (int right = 0; right < n; right++) {
            int r = nums[right];
            map.put(r, map.getOrDefault(r, 0) + 1);

            windowSize++;

            if (map.get(r) == k + 1) {
                breachCount++;
            }

            if (breachCount > 0) {
                int l = nums[left++];

                map.put(l, map.get(l) - 1);

                windowSize--;

                if (map.get(l) == k) {
                    breachCount--;
                }
            }

            // if (breachCount == 0) {
            //     windowSize++;
            // }

            ans = Math.max(ans, windowSize);
        }

        // [1,3,4,2,7,9,2,10]

        return ans;
    }

    public int mySol(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap();
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.get(nums[right]) > k) {
                map.put(nums[left], map.get(nums[left]) - 1);

                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}