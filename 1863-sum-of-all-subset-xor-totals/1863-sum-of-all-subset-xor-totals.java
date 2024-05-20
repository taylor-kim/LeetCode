class Solution {
    public int subsetXORSum(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        return mySol(nums, 0, 0);
    }

    public int mySol(int[] nums, int index, int xor) {
        if (index == nums.length) return xor;

        int exclude = mySol(nums, index + 1, xor);
        int include = mySol(nums, index + 1, xor ^ nums[index]);

        return include + exclude;
    }
}