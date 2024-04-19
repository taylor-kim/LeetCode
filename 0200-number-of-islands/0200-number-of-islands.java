class Solution {
    public int numIslands(char[][] grid) {
        return mySol_dfs(grid);
    }

    public int mySol_dfs(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visit = new boolean[m][n];

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visit[i][j]) {
                    mySol_dfs(i, j, grid, visit);
                    ans++;
                }
            }
        }

        return ans;
    }

    public void mySol_dfs(int r, int c, char[][] grid, boolean[][] visit) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return;

        if (visit[r][c] || grid[r][c] == '0') return;

        visit[r][c] = true;

        mySol_dfs(r - 1, c, grid, visit);
        mySol_dfs(r + 1, c, grid, visit);
        mySol_dfs(r, c - 1, grid, visit);
        mySol_dfs(r, c + 1, grid, visit);
    }
}