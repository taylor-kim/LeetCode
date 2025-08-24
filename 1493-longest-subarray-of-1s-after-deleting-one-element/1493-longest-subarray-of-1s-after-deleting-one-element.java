class Solution {
    public int longestSubarray(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = 0;
        int left = 0;
        int countOfZero = 0;

        for (int right = 0; right < nums.length; right++) {
            countOfZero += nums[right] == 0 ? 1 : 0;

            while (countOfZero > 1) {
                countOfZero -= nums[left++] == 0 ? 1 : 0;
            }

            ans = Math.max(ans, right - left);
        }

        return ans;
    }
}