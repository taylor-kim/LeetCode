class Solution {
    public int stoneGameII(int[] piles) {
        return official_bottomup(piles);
    }

    public int official_bottomup(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];

        int[][] dp = new int[n + 1][n + 1];

        suffixSum[n - 1] = piles[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = piles[i] + suffixSum[i + 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {
                if(i + 2 * m < n) {
                    int min = Integer.MAX_VALUE;
                    for (int x = 1; x <= 2 * m; x++) {
                        min = Math.min(min, dp[i + x][Math.max(x, m)]);
                    }

                    dp[i][m] = suffixSum[i] - min;
                } else {
                    dp[i][m] = suffixSum[i];
                }
            }
        }

        return dp[0][1];
    }

    public int official_topdown(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];

        suffixSum[n - 1] = piles[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = piles[i] + suffixSum[i + 1];
        }

        return official_topdown(0, 1, suffixSum, new Integer[n][n]);
    }

    public int official_topdown(int index, int m, int[] suffixSum, Integer[][] memo) {
        if (index + 2 * m >= suffixSum.length) return suffixSum[index];

        if (memo[index][m] != null) {
            return memo[index][m];
        }

        int res = Integer.MAX_VALUE;

        for (int i = 1; i <= 2 * m; i++) {
            res = Math.min(res, official_topdown(index + i, Math.max(i, m), suffixSum, memo));
        }

        return memo[index][m] = suffixSum[index] - res;
    }

    public int mySol_fail(int[] piles) {
        int[] pSum = new int[piles.length + 1];

        for (int i = 0; i < piles.length; i++) {
            pSum[i + 1] = pSum[i] + piles[i];
        }

        return topdown(piles, 0, 1, true, pSum);
    }

    public int topdown(int[] piles, int index, int m, boolean isAlice, int[] pSum) {
        if (index >= piles.length) return 0;

        int ans = 0;

        int limit = Math.min(index + 2 * m, piles.length);

        for (int i = 1; i <= 2 * m && index + i < piles.length; i++) {
            int sum = isAlice ? pSum[index + i] - pSum[index] : 0;
            ans = Math.max(ans, sum + topdown(piles, index + i, Math.max(i, m), !isAlice, pSum));
        }

        return pSum[index] - ans;
    }
}