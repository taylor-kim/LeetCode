class Solution {
    public int jump(int[] nums) {
        return bottomup(nums);
    }
    
    public int bottomup(int[] nums) {
        int n = nums.length;

        if (n == 1) return 0;

        int[] dp = new int[n];

        Arrays.fill(dp, n);
        dp[n - 1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (i + nums[i] >= n - 1) {
                dp[i] = 1;
            } else {
                for (int j = nums[i]; j >= 0; j--) {
                    dp[i] = Math.min(dp[i], 1 + dp[i + j]);
                }
            }
        }

        return dp[0];
    }

    public int topdown(int[] nums) {
        return topdown(nums, 0, new Integer[nums.length]);
    }

    public int topdown(int[] nums, int index, Integer[] memo) {
        if (index >= nums.length - 1) return 0;

        if (memo[index] != null) return memo[index];

        int ans = nums.length;

        for (int i = index + 1; i <= index + nums[index]; i++) {
            ans = Math.min(ans, 1 + topdown(nums, i, memo));
        }

        return memo[index] = ans;
    }
}