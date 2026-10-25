class Solution {
    public int closedIsland(int[][] grid) {
        return mySol(grid);
    }

    public int mySol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) dfs(grid, i, 0);
            if (grid[i][n - 1] == 0) dfs(grid, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0) dfs(grid, 0, j);
            if (grid[m - 1][j] == 0) dfs(grid, m - 1, j);
        }

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j);
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] grid, int y, int x) {
        if (y < 0 || y >= grid.length || x < 0 || x >= grid[0].length) return;

        if (grid[y][x] != 0) return;

        grid[y][x] = 2;

        dfs(grid, y + 1, x);
        dfs(grid, y - 1, x);
        dfs(grid, y, x + 1);
        dfs(grid, y, x - 1);
    }
}