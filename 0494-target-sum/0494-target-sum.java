class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return mySol_bf(nums, target);
    }

    public int mySol_bf(int[] nums, int target) {
        return topdown(nums, 0, target);
    }

    private int topdown(int[] nums, int index, int target) {
        if (index >= nums.length) return target == 0 ? 1 : 0;

        int plus = topdown(nums, index + 1, target + nums[index]);
        int minus = topdown(nums, index + 1, target - nums[index]);

        return plus + minus;
    }
}