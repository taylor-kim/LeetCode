class Solution {
    public int minFallingPathSum(int[][] grid) {
        return mySol_dfs_tle(grid);
    }

    public int mySol_sort(int[][] grid) {
        int n = grid.length;
        int[][][] mat = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = new int[] {grid[i][j], j};
            }

            Arrays.sort(mat[i], (a, b) -> {
                return a[0] - b[0];
            });
        }

        return -1;
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