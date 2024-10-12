class Solution {
    public int uniquePaths(int m, int n) {
        return try_20241012_bottomup(m, n);
    }

    public int try_20241012_bottomup_spaceopt2(int m, int n) {
        int[] dp = new int[n];
        dp[n - 1] = 1;

        for (int r = m - 2; r >= 0; r--) {
            for (int c = n - 2; c >= 0; c--) {
                dp[c] = dp[c] + dp[c + 1];
            }
        }

        return dp[0];
    }

    public int try_20241012_bottomup_spaceopt(int m, int n) {
        int[] dp = new int[n + 1];
        // Arrays.fill(dp, 1);
        dp[1] = 1;

        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                dp[c] = dp[c - 1] + dp[c];
            }
        }

        return dp[n];
    }

    public int try_20241012_bottomup(int m, int n) {
        int[][] dp = new int[m][n];
        
        for (int r = 0; r < dp.length; r++) {
            dp[r][n - 1] = 1;
        }

        Arrays.fill(dp[m - 1], 1);

        for (int r = m - 2; r >= 0; r--) {
            for (int c = n - 2; c >= 0; c--) {
                dp[r][c] = dp[r + 1][c] + dp[r][c + 1];
            }
        }

        return dp[0][0];
    }

    public int try_20241012(int m, int n) {
        Integer[][] dp = new Integer[m][n];
        dp[m - 1][n - 1] = 1;

        return try_20241012(0, 0, dp);
    }

    public int try_20241012(int r, int c, Integer[][] dp) {
        if (r >= dp.length || c >= dp[0].length) return 0;

        if (dp[r][c] != null) return dp[r][c];

        int ans = 0;

        ans += try_20241012(r + 1, c, dp);
        ans += try_20241012(r , c + 1, dp);

        return dp[r][c] = ans;
    }
}