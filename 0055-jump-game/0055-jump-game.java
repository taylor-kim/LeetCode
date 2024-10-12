class Solution {
    public boolean canJump(int[] nums) {
        return good_greedy(nums);
    }

    public boolean good_greedy(int[] nums) {
        int far = 0;

        for (int i = 0; i <= far && i < nums.length; i++) {
            far = Math.max(far, i + nums[i]);

            if (far >= nums.length - 1) return true;
        }

        return false;
    }

    public boolean try_20241012_bottomup(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;

        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= n - 1) {
                dp[i] = true;
            } else {
                for (int j = nums[i]; j > 0; j--) {
                    if (dp[i + j]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[0];
    }

    public boolean try_20241012(int[] nums) {
        return topdown(nums, 0, new Boolean[nums.length]);
    }

    private boolean topdown(int[] nums, int index, Boolean[] memo) {
        if (index >= nums.length - 1) return true;

        if (memo[index] != null) {
            return memo[index];
        }

        boolean ans = false;

        for (int i = index + 1; i <= index + nums[index]; i++) {
            ans |= topdown(nums, i, memo);

            if (ans) break;
        }

        return memo[index] = ans;
    }
}