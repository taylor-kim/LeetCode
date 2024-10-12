class Solution {
    public boolean canJump(int[] nums) {
        return try_20241012(nums);
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