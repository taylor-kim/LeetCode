class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return mySol(nums, k);
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