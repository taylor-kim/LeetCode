class Solution {
    public boolean stoneGame(int[] piles) {
        return mySol2(piles);
    }

    public boolean mySol2(int[] piles) {
        int n = piles.length;
        int[] dp = piles.clone();

        for (int diff = 1; diff < n; diff++) {
            for (int left = n - diff - 1; left >= 0; left--) {
                int right = left + diff;

                dp[right] = Math.max(
                    piles[left] - dp[right],
                    piles[right] - dp[right - 1]
                );
            }
        }

        return dp[n - 1] > 0;
    }

    public boolean mySol(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        for (int diff = 1; diff < n; diff++) {
            for (int left = 0; left < n - diff; left++) {
                int right = left + diff;

                dp[left][right] = Math.max(
                    piles[left] - dp[left + 1][right],
                    piles[right] - dp[left][right - 1]
                );
            }
        }

        return dp[0][n - 1] > 0;
    }
}