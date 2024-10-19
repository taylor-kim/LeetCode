class Solution {
    public int jump(int[] nums) {
        return topdown(nums);
    }

    public int topdown(int[] nums) {
        return topdown(nums, 0, new Integer[nums.length]);
    }

    public int topdown(int[] nums, int index, Integer[] memo) {
        if (index >= nums.length - 1) return 0;

        if (memo[index] != null) return memo[index];

        int ans = nums.length;

        for (int i = index + 1; i <= index + nums[index]; i++) {
            ans = Math.min(ans, 1 + topdown(nums, i, memo));
        }

        return memo[index] = ans;
    }
}