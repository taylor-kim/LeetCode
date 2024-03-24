class Solution {
    public int findDuplicate(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = -1;

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);

            nums[index - 1] *= -1;

            if (nums[index - 1] > 0) {
                ans = index;
                break;
            }
        }

        // for (int i = 0; i < nums.length; i++) {
        //     nums[i] = Math.abs(nums[i]);
        // }

        return ans;
    }
}