class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return official_bottomup(nums, target);
    }

    public int official_bottomup(int[] nums, int target) {
        int max = 0;
        for (int num : nums) max += num;

        if (Math.abs(target) > max) return 0;

        int[][] dp = new int[nums.length + 1][max * 2 + 1];
        dp[0][nums[0] + max] = 1;
        dp[0][-nums[0] + max] += 1;

        for (int i = 1; i < nums.length; i++) {
            for (int sum = -max; sum <= max; sum++) {
                if (dp[i - 1][sum + max] > 0) {
                    dp[i][sum + nums[i] + max] += dp[i - 1][sum + max];
                    dp[i][sum - nums[i] + max] += dp[i - 1][sum + max];
                }
            }
        }

        return dp[nums.length - 1][target + max];
    }

    public int official_topdown(int[] nums, int target) {
        int max = 0;
        for (int num : nums) max += num;

        return official_topdown(nums, target, 0, 0, new Integer[nums.length][max * 2 + 1], max);
    }

    private int official_topdown(int[] nums, int target, int index, int sum, Integer[][] memo, int shift) {
        if (index >= nums.length) return sum == target ? 1: 0;

        if (memo[index][sum + shift] != null) return memo[index][sum + shift];

        int plus = official_topdown(nums, target, index + 1, sum + nums[index], memo, shift);

        int minus = official_topdown(nums, target, index + 1, sum - nums[index], memo, shift);

        return memo[index][sum + shift] = plus + minus;
    }

    public int try_space_opt(int[] nums, int target) {
        int max = Math.abs(target);

        for (int num : nums) max += num;

        int[] dp = new int[max * 2 + 1];

        dp[max] = 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            int[] newDp = new int[max * 2 + 1];

            for (int sum = -max; sum <= max; sum++) {
                if (sum + nums[i] + max >= 0 && sum + nums[i] + max <= 2 * max) {
                    newDp[sum + max] += dp[sum + nums[i] + max];
                }

                if (sum - nums[i] + max >= 0 && sum - nums[i] + max <= 2 * max) {
                    newDp[sum + max] += dp[sum - nums[i] + max];
                }
            }

            dp = newDp;
        }

        // for (int[] row : dp) {
        //     System.out.println(Arrays.toString(row));
        // }

        // System.out.println(String.format("target:%d, max:%d, j:%d", target, max, target + max));

        return dp[target + max];
    }

    public int mySol_bottomup_fail_and_gpt(int[] nums, int target) {
        int max = Math.abs(target);

        for (int num : nums) max += num;

        int[][] dp = new int[nums.length + 1][max * 2 + 1];

        dp[nums.length][max] = 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            for (int sum = -max; sum <= max; sum++) {
                if (sum + nums[i] + max >= 0 && sum + nums[i] + max <= 2 * max) {
                    dp[i][sum + max] += dp[i + 1][sum + nums[i] + max];
                }

                if (sum - nums[i] + max >= 0 && sum - nums[i] + max <= 2 * max) {
                    dp[i][sum + max] += dp[i + 1][sum - nums[i] + max];
                }
            }
        }

        // for (int[] row : dp) {
        //     System.out.println(Arrays.toString(row));
        // }

        // System.out.println(String.format("target:%d, max:%d, j:%d", target, max, target + max));

        return dp[0][target + max];
    }

    public int mySol_bf(int[] nums, int target) {
        int max = Math.abs(target);

        for (int num : nums) max += num;

        return topdown(nums, 0, target, new Integer[nums.length][max * 2 + 1], max);
    }

    private int topdown(int[] nums, int index, int target, Integer[][] memo, int shift) {
        if (index >= nums.length) return target == 0 ? 1 : 0;

        if (memo[index][target + shift] != null) {
            return memo[index][target + shift];
        }

        int plus = topdown(nums, index + 1, target + nums[index], memo, shift);
        int minus = topdown(nums, index + 1, target - nums[index], memo, shift);

        return memo[index][target + shift] = plus + minus;
    }
}