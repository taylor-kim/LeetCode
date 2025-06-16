class Solution {
    public int maximumDifference(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = -1;
        int min = (int)1e9 + 1;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            ans = Math.max(ans, num - min);

            min = Math.min(min, num);
        }

        return ans > 0 ? ans : -1;
    }
}