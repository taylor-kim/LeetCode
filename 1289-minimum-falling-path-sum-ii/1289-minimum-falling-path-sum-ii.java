class Solution {
    public int minFallingPathSum(int[][] grid) {
        return official_nn(grid);
    }

    public int official_nn(int[][] grid) {
        int n = grid.length;

        int nextMinC1 = -1;
        int nextMinC2 = -1;

        int[][] dp = new int[n][n];

        for (int col = 0; col < n; col++) {
            dp[n - 1][col] = grid[n - 1][col];

            if (nextMinC1 == -1 || dp[n - 1][col] < dp[n - 1][nextMinC1]) {
                nextMinC2 = nextMinC1;
                nextMinC1 = col;
            } else if (nextMinC2 == -1 || dp[n - 1][col] < dp[n - 1][nextMinC2]) {
                nextMinC2 = col;
            }
        }

        for (int row = n - 2; row >= 0; row--) {
            int minC1 = -1;
            int minC2 = -1;

            for (int col = 0; col < n; col++) {
                if (col != nextMinC1) {
                    dp[row][col] = dp[row + 1][nextMinC1] + grid[row][col];
                } else {
                    dp[row][col] = dp[row + 1][nextMinC2] + grid[row][col];
                }

                if (minC1 == -1 || dp[row][col] < dp[row][minC1]) {
                    minC2 = minC1;
                    minC1 = col;
                } else if (minC2 == -1 || dp[row][col] < dp[row][minC2]) {
                    minC2 = col;
                }
            }

            nextMinC1 = minC1;
            nextMinC2 = minC2;
        }

        return dp[0][nextMinC1];
    }

    public int try_bottomup_spaceopt(int[][] grid) {
        int n = grid.length;
        int[] dp = new int[n];

        for (int col = 0; col < n; col++) {
            dp[col] = grid[n - 1][col];
        }

        for (int row = n - 2; row >= 0; row--) {
            int[] curDp = new int[n];
            for (int col = 0; col < n; col++) {
                int min = Integer.MAX_VALUE;

                for (int nextCol = 0; nextCol < n; nextCol++) {
                    if (col != nextCol) {
                        min = Math.min(min, dp[nextCol]);
                    }
                }

                curDp[col] = grid[row][col] + min;
            }

            dp = curDp;
        }

        int ans = Integer.MAX_VALUE;

        for (int cand : dp) {
            ans = Math.min(ans, cand);
        }

        return ans;
    }

    public int official_bottomup(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];

        for (int col = 0; col < n; col++) {
            dp[n - 1][col] = grid[n - 1][col];
        }

        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col < n; col++) {
                int min = Integer.MAX_VALUE;

                for (int nextCol = 0; nextCol < n; nextCol++) {
                    if (col != nextCol) {
                        min = Math.min(min, dp[row + 1][nextCol]);
                    }
                }

                dp[row][col] = grid[row][col] + min;
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int cand : dp[0]) {
            ans = Math.min(ans, cand);
        }

        return ans;
    }

    public int mySol_dfs_tle(int[][] grid) {
        int n = grid.length;
        int ans = Integer.MAX_VALUE;

        Integer[][] memo = new Integer[n][n];

        for (int col = 0; col < n; col++) {
            ans = Math.min(ans, dfs(grid, 0, col, memo));
        }

        return ans;
    }

    public int dfs(int[][] grid, int row, int col, Integer[][] memo) {
        if (row >= grid.length) return 0;

        if (memo[row][col] != null) {
            return memo[row][col];
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < grid[row].length; i++) {
            if (i == col) continue;

            int sub = grid[row][col] + dfs(grid, row + 1, i, memo);

            ans = Math.min(ans, sub);
        }

        return memo[row][col] = ans == Integer.MAX_VALUE ? grid[row][col] : ans;
    }
}