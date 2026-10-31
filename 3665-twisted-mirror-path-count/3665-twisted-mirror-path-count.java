class Solution {
    public int uniquePaths(int[][] grid) {
        return try_bottomup_space_opt(grid);
    }

    public int try_bottomup_space_opt(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[n][2];
        int mod = (int)1e9 + 7;
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    if (grid[i - 1][j] == 0) {
                        dp[j][0] = (dp[j][0] + dp[j][1]) % mod;
                    } else {
                        dp[j][0] = dp[j][1];
                    }
                }

                if (j > 0) {
                    if (grid[i][j - 1] == 0) {
                        dp[j][1] = (dp[j - 1][0] + dp[j - 1][1]) % mod;
                    } else {
                        dp[j][1] = dp[j - 1][0];
                    }
                }
            }
        }

        return (dp[n - 1][0] + dp[n - 1][1]) % mod;
    }

    public int try_bottomup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][2];
        int mod = (int)1e9 + 7;
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    if (grid[i - 1][j] == 0) {
                        dp[i][j][1] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % mod;
                    } else {
                        dp[i][j][1] = dp[i - 1][j][0];
                    }
                }

                if (j > 0) {
                    if (grid[i][j - 1] == 0) {
                        dp[i][j][0] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % mod;
                    } else {
                        dp[i][j][0] = dp[i][j - 1][1];
                    }
                }
            }
        }

        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % mod;
    }

    public int mySol_hint_by_gemini(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        return topdown(grid, 0, 0, 0, new Integer[2][m][n]);
    }

    public int topdown(int[][] grid, int dir, int y, int x, Integer[][][] memo) {
        if (y < 0 || y >= grid.length || x < 0 || x >= grid[0].length) return 0;

        if (y == grid.length - 1 && x == grid[0].length - 1) return 1;

        if (memo[dir][y][x] != null) return memo[dir][y][x];

        int mod = (int)1e9 + 7;

        if (grid[y][x] == 0) {
            memo[dir][y][x] = (topdown(grid, 1, y + 1, x, memo) + topdown(grid, 0, y, x + 1, memo)) % mod;
        } else if (dir == 0) {
            memo[dir][y][x] = topdown(grid, 1, y + 1, x, memo);
        } else {
            memo[dir][y][x] = topdown(grid, 0, y, x + 1, memo);
        }

        return memo[dir][y][x];
    }
}