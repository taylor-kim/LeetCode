class Solution {
    public int stoneGameV(int[] stoneValue) {
        return mySol(stoneValue);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
        }

        return topdown(pSum, 0, n - 1, new Integer[n][n]);
    }

    private int topdown(int[] pSum, int left, int right, Integer[][] memo) {
        if (left >= right) return 0;

        if (memo[left][right] != null) return memo[left][right];

        int ans = 0;

        for (int i = left; i < right; i++) {
            int lSum = pSum[i + 1] - pSum[left];
            int rSum = pSum[right + 1] - pSum[i + 1];

            if (lSum > rSum) {
                ans = Math.max(ans, rSum + topdown(pSum, i + 1, right, memo));
            } else if (lSum < rSum) {
                ans = Math.max(ans, lSum + topdown(pSum, left, i, memo));
            } else {
                int max = Math.max(
                    rSum + topdown(pSum, i + 1, right, memo),
                    lSum + topdown(pSum, left, i, memo)
                );
                ans = Math.max(ans, max);
            }
        }

        return memo[left][right] = ans;
    }
}