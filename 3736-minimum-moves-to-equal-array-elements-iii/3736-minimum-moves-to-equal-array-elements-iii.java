class Solution {
    public int minMoves(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans += nums[n - 1] - nums[i];
        }

        return ans;
    }
}