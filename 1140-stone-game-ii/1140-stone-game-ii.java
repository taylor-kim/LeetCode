class Solution {
    public int stoneGameII(int[] piles) {
        return try_suffixSum_bottomup(piles);
    }

    public int try_suffixSum_bottomup(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = piles[i] + suffixSum[i + 1];
        }

        int[][] dp = new int[n + 1][n + 1];

        // for (int i = 0; i < n; i++) {
        //     for (int m = 1; m <= n; m++) {
        //         if (i + 2 * m >= n) {
        //             dp[i][m] = suffixSum[i];
        //         }
        //     }
        // }

        for (int i = 0; i < n; i++) {
            dp[i][n - i] = suffixSum[i];
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int m = n; m >= 1; m--) {
                int res = Integer.MAX_VALUE;

                // for (int x = 1; x <= 2 * m && i + x <= n; x++) {
                //     res = Math.min(res, dp[i + x][Math.max(x, m)]);
                // }

                for (int x = Math.min(2 * m, n - i); x >= 1; x--) {
                    res = Math.min(res, dp[i + x][Math.max(x, m)]);
                }

                dp[i][m] = suffixSum[i] - res;
            }
        }

        return dp[0][1];
    }

    public int editorial_suffixSum_topdown(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = piles[i] + suffixSum[i + 1];
        }

        return suffixSumTopdown(suffixSum, 0, 1, new Integer[n][n + 1]);
    }

    private int suffixSumTopdown(int[] suffixSum, int index, int m, Integer[][] memo) {
        if (index + (2 * m) >= suffixSum.length) {
            return suffixSum[index];
        }

        if (memo[index][m] != null) return memo[index][m];

        int othersMin = Integer.MAX_VALUE;

        for (int x = 1; x <= 2 * m; x++) {
            int others = suffixSumTopdown(suffixSum, index + x, Math.max(x, m), memo);

            othersMin = Math.min(othersMin, others);
        }

        return memo[index][m] = suffixSum[index] - othersMin;
    }

    public int mySol(int[] piles) {
        int total = 0;

        for (int num : piles) {
            total += num;
        }

        int delta = topdown(piles, 0, 1, new Integer[piles.length][piles.length + 1]);

        // a + b = total
        // a - b = delta

        // a = total - b
        // a = delta + b
        // total - b == delta + b
        // total - delta = 2b
        // b = (total - delta) / 2
        // a = total - (total - delta) / 2

        return total - (total - delta) / 2;
    }

    public int topdown(int[] piles, int index, int m, Integer[][] memo) {
        if (index >= piles.length) return 0;

        if (memo[index][m] != null) return memo[index][m];

        int ans = Integer.MIN_VALUE;
        int stones = 0;

        for (int x = 1; x <= 2 * m && index + x - 1 < piles.length; x++) {
            stones += piles[index + x - 1];

            int sub = stones - topdown(piles, index + x, Math.max(x, m), memo);

            ans = Math.max(ans, sub);
        }

        return memo[index][m] = ans;
    }
}