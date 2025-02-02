class Solution {
    public boolean check(int[] nums) {
        return mySol(nums);
    }

    public boolean mySol(int[] nums) {
        int min = nums[0];
        boolean foundRotated = false;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                if (foundRotated) return false;
                foundRotated = true;
            }

            if (foundRotated && min < nums[i]) {
                return false;
            }
        }

        return true;
    }
}