class Solution {
    public int maxPathScore(int[][] grid, int k) {
        return bottomup(grid, k);
    }

    public int bottomup(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m + 1][n + 1][k + 1];
        
        for (int i = m; i >= 0; i--) {
            for (int d = 0; d <= k; d++) {
                dp[i][n][d] = -1;
            }
        }

        for (int j = n; j >= 0; j--) {
            for (int d = 0; d <= k; d++) {
                dp[m][j][d] = -1;
            }
        }

        int initCost = Math.min(grid[m - 1][n - 1], 1);

        for (int d = 0; d <= k; d++) {
            if (d - initCost >= 0) {
                dp[m - 1][n - 1][d] = grid[m - 1][n - 1];
            } else {
                dp[m - 1][n - 1][d] = -1;
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    continue;
                }

                for (int d = 0; d <= k; d++) {
                    int cost = Math.min(grid[i][j], 1);

                    if (d - cost < 0) {
                        dp[i][j][d] = -1;
                    } else {
                        int max = Math.max(dp[i + 1][j][d - cost], dp[i][j + 1][d - cost]);

                        if (max >= 0) {
                            dp[i][j][d] = grid[i][j] + max;
                        } else {
                            dp[i][j][d] = -1;
                        }
                    }
                }
            }
        }

        return dp[0][0][k];
    }

    public int mySol(int[][] grid, int k) {
        return topdown(grid, 0, 0, k, new Integer[grid.length][grid[0].length][k + 1]);
    }

    public int topdown(int[][] grid, int i, int j, int k, Integer[][][] memo) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return -1;

        int nextK = k - Math.min(grid[i][j], 1);

        if (nextK < 0) return -1;

        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];

        if (memo[i][j][k] != null) return memo[i][j][k];

        int right = topdown(grid, i, j + 1, nextK, memo);
        int down = topdown(grid, i + 1, j, nextK, memo);

        int max = Math.max(right, down);

        return memo[i][j][k] = max < 0 ? -1 : max + grid[i][j];
    }
}