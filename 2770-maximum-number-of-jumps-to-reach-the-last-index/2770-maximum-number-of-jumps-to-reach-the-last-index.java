class Solution {
    public int maximumJumps(int[] nums, int target) {
        return try_bottomup(nums, target);
    }

    public int try_bottomup(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[j] - nums[i]) <= target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        return dp[n - 1] < 0 ? -1 : dp[n - 1];
    }

    public int mySol(int[] nums, int target) {
        // -target <= nums[j] - nums[i] <= target
        // nums[i] - target <= nums[j]
        // nums[j] <= target + nums[i]
        // nums[i] - target <= nums[j] <= target + nums[i]

        return topdown2(nums, target, 0, new Integer[nums.length]);
    }

    public int topdown2(int[] nums, int target, int index, Integer[] memo) {
        if (index == nums.length - 1) return 0;

        if (memo[index] != null) return memo[index];

        int ans = -1;

        for (int j = index + 1; j < nums.length; j++) {
            if ((long)nums[index] - target <= nums[j] && nums[j] <= (long)nums[index] + target) {
                int sub = topdown2(nums, target, j, memo);

                if (sub >= 0) {
                    ans = Math.max(ans, sub + 1);
                }
            }
        }

        return memo[index] = ans;
    }

    //fail
    public int topdown(int[] nums, int target, int prev, int index) {
        if (index == nums.length - 1) return 0;

        int ans = -1;

        if ((long)nums[prev] - target <= nums[index] && nums[index] <= (long)target + nums[prev]) {
            int include = topdown(nums, target, index, index + 1);

            if (include >= 0) {
                ans = 1 + include;
            }
        }

        int exclude = topdown(nums, target, prev, index + 1);

        if (exclude >= 0) {
            ans = Math.max(ans, 1 + exclude);
        }

        return ans;
    }
}