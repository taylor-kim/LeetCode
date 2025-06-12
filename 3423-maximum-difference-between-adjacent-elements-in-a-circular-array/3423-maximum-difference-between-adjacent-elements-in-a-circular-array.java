class Solution {
    public int maxAdjacentDistance(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, Math.abs(nums[i] - nums[(i + 1) % n]));
        }

        return ans;
    }
}