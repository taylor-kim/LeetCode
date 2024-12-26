class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return mySol_bottomup_gpt(nums, target);
    }

    public int mySol_bottomup_gpt(int[] nums, int target) {
    int max = 0;
    for (int num : nums) {
        max += Math.abs(num); // Find the max possible sum
    }

    // If the target is outside the bounds, there's no solution.
    if (Math.abs(target) > max) {
        return 0;
    }

    int[][] dp = new int[nums.length + 1][2 * max + 1]; // dp[i][sum + max] stores the number of ways to form 'sum'
    dp[nums.length][max] = 1; // Base case: at the last element, sum = 0 is the only valid way

    // Bottom-up DP: iterate over the numbers and calculate the ways to reach target sums
    for (int i = nums.length - 1; i >= 0; i--) {
        for (int sum = -max; sum <= max; sum++) {
            // Calculate the new sum if you add or subtract the current number
            if (sum + nums[i] <= max && sum + nums[i] >= -max) {
                dp[i][sum + nums[i] + max] += dp[i + 1][sum + max];
            }
            if (sum - nums[i] <= max && sum - nums[i] >= -max) {
                dp[i][sum - nums[i] + max] += dp[i + 1][sum + max];
            }
        }
    }

    return dp[0][target + max];
}


    public int mySol_bottomup(int[] nums, int target) {
        int max = Math.abs(target);

        for (int num : nums) max += num;

        int[][] dp = new int[nums.length + 1][max * 2 + 1];

        dp[nums.length][max] = 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = Math.abs(target); j >= 0; j--) {
                int subTarget = j;
                if (target < 0) {
                    subTarget *= -1;
                }
                int plus = dp[i + 1][nums[i] + subTarget + max];
                int minus = dp[i + 1][-nums[i] + subTarget + max];

                dp[i][subTarget + max] += plus + minus;
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