class Solution {
    public int[] leftRightDifference(int[] nums) {
        return try_20260606(nums);
    }

    public int[] try_20260606(int[] nums) {
        int n = nums.length;
        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
        }

        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            ans[i] = Math.abs(pSum[i] - (pSum[n] - pSum[i + 1]));
        }

        return ans;
    }

    public int[] mySol(int[] nums) {
        int n = nums.length;
        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
        }

        for (int i = 0; i < n; i++) {
            nums[i] = Math.abs(pSum[i] - (pSum[n] - pSum[i + 1]));
        }

        return nums;
    }
}