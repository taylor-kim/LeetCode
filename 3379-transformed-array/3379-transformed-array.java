class Solution {
    public int[] constructTransformedArray(int[] nums) {
        return mySol(nums);
    }

    public int[] mySol(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int move = ((num % n) + n) % n;
		    ans[i] = nums[(i + move) % n];
        }

        return ans;
    }
}