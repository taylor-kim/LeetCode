class Solution {
    public boolean hasValidPath(char[][] grid) {
        return mySol_mle(grid);
    }

    public boolean mySol2(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m + 1][n + 1][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int delta = grid[i][j] == '(' ? 1 : -1;

                dp[i + 1][j + 1][0] = dp[i][j + 1][0] < 0 ? -1 : dp[i][j + 1][0] + delta;
                dp[i + 1][j + 1][1] = dp[i + 1][j][1] < 0 ? - 1 : dp[i + 1][j][1] + delta;

                System.out.print("i:%d, j:%d, dp:%s | ".formatted(i, j, Arrays.toString(dp[i + 1][j + 1])));
            }
            System.out.println("");
        }

        return dp[m][n][0] == 0 || dp[m][n][1] == 0;
    }

    public boolean mySol_mle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // System.out.println("m:%d, n:%d".formatted(m, n));

        return topdown(grid, 0, 0, 0, new HashMap());
    }

    public boolean topdown(char[][] grid, int i, int j, int open, Map<String, Boolean> memo) {
        int m = grid.length;
        int n = grid[0].length;
        int buffer = m * n;

        if (i < 0 || i >= m || j < 0 || j >= n || open < 0) return false;

        open += grid[i][j] == '(' ? 1 : -1;

        String key = new StringBuilder().append(i).append(",").append(j).append(",").append(open).toString();

        if (memo.containsKey(key)) return memo.get(key);

        if (i == m - 1 && j == n - 1) return open == 0;

        boolean ans = topdown(grid, i + 1, j, open, memo) || topdown(grid, i, j + 1, open, memo);

        memo.put(key, ans);

        return ans;
    }
}