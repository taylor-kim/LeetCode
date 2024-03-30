class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        int n = nums.length;

        int[] dp = new int[n + 1];

        int left = 0;

        int product = 1;
        
        for (int right = 0; right < n; right++) {
            product *= nums[right];

            while (left <= right && product >= k) {
                product /= nums[left++];
            }

            dp[right + 1] += dp[right] + right - left + 1;
        }

        return dp[n];
    }
}