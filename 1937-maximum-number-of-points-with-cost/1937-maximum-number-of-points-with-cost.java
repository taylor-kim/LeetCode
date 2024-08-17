class Solution {
    public long maxPoints(int[][] points) {
        return hint_bottomup_opt(points);
    }

    public long hint_bottomup_opt(int[][] points) {
        int m = points.length;
        int n = points[0].length;

        long[] dp = new long[n];

        for (int r = m - 1; r >= 0; r--) {
            for (int c = 0; c < n; c++) {
                dp[c] = dp[c] + points[r][c];
            }

            for (int c = 1; c < n; c++) {
                dp[c] = Math.max(dp[c], dp[c - 1] - 1);
            }

            for (int c = n - 2; c >= 0; c--) {
                dp[c] = Math.max(dp[c], dp[c + 1] - 1);
            }
        }

        long ans = 0;

        for (long cand : dp) {
            ans = Math.max(ans, cand);
        }

        return ans;
    }

    public long hint_bottomup(int[][] points) {
        int m = points.length;
        int n = points[0].length;

        long[][] dp = new long[m + 1][n];

        for (int r = m - 1; r >= 0; r--) {
            for (int c = 0; c < n; c++) {
                dp[r][c] = dp[r + 1][c] + points[r][c];
            }

            for (int c = 1; c < n; c++) {
                dp[r][c] = Math.max(dp[r][c], dp[r][c - 1] - 1);
            }

            for (int c = n - 2; c >= 0; c--) {
                dp[r][c] = Math.max(dp[r][c], dp[r][c + 1] - 1);
            }
        }

        long ans = 0;

        for (long cand : dp[0]) {
            ans = Math.max(ans, cand);
        }

        return ans;
    }

    public long mySol_topdown(int[][] points) {
        int m = points.length;
        int n = points[0].length;

        long ans = 0;

        Long[][] memo = new Long[m][n];

        for (int c = 0; c < n; c++) {
            ans = Math.max(ans, mySol_topdown(points, 0, c, memo));
        }

        return ans;
    }

    public long mySol_topdown(int[][] points, int r, int c, Long[][] memo) {
        if (r >= points.length) return 0L;

        if (memo[r][c] != null) {
            return memo[r][c];
        }

        long ans = 0;

        for (int i = 0; i < points[0].length; i++) {
            int loosing = Math.abs(c - i);

            ans = Math.max(ans, points[r][c] - loosing + mySol_topdown(points, r + 1, i, memo));
        }

        return memo[r][c] = ans;
    }
}