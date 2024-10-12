class Solution {
    public int uniquePaths(int m, int n) {
        return try_20241012(m, n);
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