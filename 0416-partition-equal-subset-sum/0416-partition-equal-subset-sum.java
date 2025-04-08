class Solution {
    public boolean canPartition(int[] nums) {
        return try_bottomup_spaceopt(nums);
    }

    public boolean try_bottomup_spaceopt(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) return false;

        sum /= 2;

        int n = nums.length;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = sum; j >= 0; j--) {
                if (j >= nums[i]) {
                    dp[j] = dp[j - nums[i]] || dp[j];
                }
            }
        }

        return dp[sum];
    }

    public boolean try_bottomup(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) return false;

        sum /= 2;

        int n = nums.length;

        boolean[][] dp = new boolean[n + 1][sum + 1];
        dp[n][0] = true;

        for (int i = n - 1; i >= 0; i--) {
            // dp[i][0] = true;
            for (int j = 1; j <= sum; j++) {
                if (j >= nums[i]) {
                    dp[i][j] |= dp[i + 1][j - nums[i]] || dp[i + 1][j];
                } else {
                    dp[i][j] |= dp[i + 1][j];
                }
            }
        }

        return dp[0][sum];
    }

    public boolean try_20250408(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) return false;

        return topdown(nums, 0, sum / 2, new Boolean[nums.length][sum / 2 + 1]);
    }

    private boolean topdown(int[] nums, int index, int remain, Boolean[][] memo) {
        if (remain < 0) return false;

        if (remain == 0) return true;

        if (index >= nums.length) return remain == 0;

        if (memo[index][remain] != null) return memo[index][remain];

        return memo[index][remain] = topdown(nums, index + 1, remain - nums[index], memo)
            || topdown(nums, index + 1, remain, memo);
    }
}