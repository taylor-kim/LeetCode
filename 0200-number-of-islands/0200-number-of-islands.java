class Solution {
    public int numIslands(char[][] grid) {
        return mySol_dfs(grid);
    }

    public int mySol_dfs(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    mySol_dfs(i, j, grid);
                    ans++;
                }
            }
        }

        return ans;
    }

    public void mySol_dfs(int r, int c, char[][] grid) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return;

        if (grid[r][c] != '1') return;

        grid[r][c] = '2';

        mySol_dfs(r - 1, c, grid);
        mySol_dfs(r + 1, c, grid);
        mySol_dfs(r, c - 1, grid);
        mySol_dfs(r, c + 1, grid);
    }
}