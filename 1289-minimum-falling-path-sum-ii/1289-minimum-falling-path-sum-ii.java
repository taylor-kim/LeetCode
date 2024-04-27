class Solution {
    public int minFallingPathSum(int[][] grid) {
        return official_bottomup(grid);
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