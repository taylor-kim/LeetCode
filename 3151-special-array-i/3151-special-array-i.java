class Solution {
    public boolean isArraySpecial(int[] nums) {
        return mySol(nums);
    }

    public boolean mySol(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] % 2 == nums[i] % 2) return false;
        }

        return true;
    }
}