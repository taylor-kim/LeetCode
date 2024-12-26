class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return mySol_bf(nums, target);
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