class Solution {
    public int islandPerimeter(int[][] grid) {
        return mySol_dfs(grid);
    }

    public int mySol_dfs(int[][] grid) {
        int[] ans = {0};

        mySol_dfs(grid, 0, 0, ans, new boolean[grid.length][grid[0].length]);

        return ans[0];
    }

    public void mySol_dfs(int[][] grid, int r, int c, int[] ans, boolean[][] visit) {
        if (r >= grid.length || r < 0 || c >= grid[0].length || c < 0) return;

        if (visit[r][c]) return;

        visit[r][c] = true;

        if (grid[r][c] == 1) {
            if (r == 0 || grid[r - 1][c] == 0) ans[0]++;
            if (r == grid.length - 1 || grid[r + 1][c] == 0) ans[0]++;

            if (c == 0 || grid[r][c - 1] == 0) ans[0]++;
            if (c == grid[0].length - 1 || grid[r][c + 1] == 0) ans[0]++;
        }

        for (int i = -1; i <= 1; i += 2) {
            mySol_dfs(grid, r + i, c, ans, visit);
        }

        for (int i = -1; i <= 1; i += 2) {
            mySol_dfs(grid, r, c + i, ans, visit);
        }
    }
}