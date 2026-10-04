class Solution {
    public boolean hasValidPath(char[][] grid) {
        return editorial_with_my_bottomup(grid);
    }

    public boolean editorial_with_my_bottomup(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] dp = new boolean[m + 1][n + 1][m + n + 1];

        if (grid[0][0] != '(' || grid[m - 1][n - 1] != ')' || (m + n - 1) % 2 != 0) return false;

        // dp[m][n][0] = true;
        dp[m - 1][n][0] = true;
        dp[m][n - 1][0] = true;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int open = 0; open < m + n; open++) {
                    int delta = grid[i][j] == '(' ? 1 : -1;
                    
                    if (open + delta < 0) continue;

                    // if (!dp[i + 1][j][open + delta] && !dp[i][j + 1][open + delta]) continue;

                    // dp[i][j][open] = true;

                    if (dp[i + 1][j][open + delta] || dp[i][j + 1][open + delta]) {
                        dp[i][j][open] = true;
                    }
                }
            }
        }

        return dp[0][0][0];
    }

    public boolean mySol2_fail(char[][] grid) {
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

    public boolean try_bottomup(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] dp = new boolean[m + 1][n + 1][m + n + 1];

        if (grid[0][0] != '(' || grid[m - 1][n - 1] != ')' || (m + n - 1) % 2 != 0) return false;

        // dp[m][n][0] = true;
        dp[m - 1][n][0] = true;
        dp[m][n - 1][0] = true;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int open = 0; open < m + n; open++) {
                    int delta = grid[i][j] == '(' ? 1 : -1;
                    
                    if (open + delta < 0) continue;

                    dp[i][j][open] = dp[i + 1][j][open + delta] || dp[i][j + 1][open + delta];
                }
            }
        }

        return dp[0][0][0];
    }

    public boolean mySol2(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        return topdown2(grid, 0, 0, 0, new Boolean[m][n][m + n]);
    }

    public boolean topdown2(char[][] grid, int i, int j, int open, Boolean[][][] memo) {
        int m = grid.length;
        int n = grid[0].length;
        int buffer = m + n + 1;

        if ((i == m - 1 && j >= n) || (i >= m && j == n - 1)) {
            return open == 0;
        }

        if (i < 0 || i >= m || j < 0 || j >= n || open < 0) return false;

        int delta = grid[i][j] == '(' ? 1 : -1;

        if (open + delta < 0) return false;

        if (memo[i][j][open] != null) return memo[i][j][open];

        return memo[i][j][open] = topdown2(grid, i + 1, j, open + delta, memo) || topdown2(grid, i, j + 1, open + delta, memo);
    }

    // public boolean mySol(char[][] grid) {
    //     int m = grid.length;
    //     int n = grid[0].length;

    //     return topdown(grid, 0, 0, 0, new Boolean[m][n][m + n]);
    // }

    // public boolean topdown(char[][] grid, int i, int j, int open, Boolean[][][] memo) {
    //     int m = grid.length;
    //     int n = grid[0].length;
    //     int buffer = m + n + 1;

    //     if (i < 0 || i >= m || j < 0 || j >= n || open < 0) return false;

    //     open += grid[i][j] == '(' ? 1 : -1;

    //     if (open < 0) return false;

    //     if (memo[i][j][open] != null) return memo[i][j][open];

    //     if (i == m - 1 && j == n - 1) return open == 0;

    //     return memo[i][j][open] = topdown(grid, i + 1, j, open, memo) || topdown(grid, i, j + 1, open, memo);
    // }
}