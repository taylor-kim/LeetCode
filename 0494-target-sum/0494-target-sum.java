class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return mySol_bf(nums, target);
    }

    public int mySol_bf(int[] nums, int target) {
        return topdown(nums, 0, target, new Integer[nums.length][3001]);
    }

    private int topdown(int[] nums, int index, int target, Integer[][] memo) {
        if (index >= nums.length) return target == 0 ? 1 : 0;

        int shift = 1000;

        if (memo[index][target + shift] != null) {
            return memo[index][target + shift];
        }

        int plus = topdown(nums, index + 1, target + nums[index], memo);
        int minus = topdown(nums, index + 1, target - nums[index], memo);

        return memo[index][target + shift] = plus + minus;
    }
}