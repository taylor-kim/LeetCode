class Solution {
    public boolean canPartition(int[] nums) {
        return try_20250408(nums);
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