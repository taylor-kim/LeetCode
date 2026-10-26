class Solution {
    public int countIslands(int[][] grid, int k) {
        return mySol(grid, k);
    }

    public int mySol(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0 && dfs(grid, i, j, k) % k == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private int dfs(int[][] grid, int y, int x, int k) {
        if (y < 0 || y >= grid.length || x < 0 || x >= grid[0].length || grid[y][x] == 0) return 0;

        int sum = grid[y][x];

        grid[y][x] = 0;

        return (sum
            + dfs(grid, y + 1, x, k)
            + dfs(grid, y - 1, x, k)
            + dfs(grid, y, x + 1, k)
            + dfs(grid, y, x - 1, k)) % k;
    }
}