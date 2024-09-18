class Solution {
    public int longestSubarray(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
        }

        int ans = 1;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                count++;
            } else {
                count = 0;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}