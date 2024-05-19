class Solution {
    public int getMaximumGold(int[][] grid) {
        return official_backtrack_dfs(grid);
    }

    private int[][] dirs = {
        {0, 1}
        , {0, -1}
        , {1, 0}
        , {-1, 0}
    };

    public int official_backtrack_dfs(int[][] grid) {
        int ans = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ans = Math.max(ans, official_dfs(grid, i, j));
            }
        }

        return ans;
    }

    public int official_dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) return 0;

        int origin = grid[r][c];

        grid[r][c] = 0;

        int max = 0;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            max = Math.max(max, official_dfs(grid, nr, nc));
        }

        grid[r][c] = origin;

        return origin + max;
    }

    public int mySol_topdown(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    ans = Math.max(ans, dfs(grid, i, j, new boolean[m][n]));
                }
            }
        }

        return ans;
    }

    public int dfs(int[][] grid, int r, int c, boolean[][] visit) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return 0;

        if (visit[r][c] || grid[r][c] == 0) return 0;

        visit[r][c] = true;

        int max = 0;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            max = Math.max(max, dfs(grid, nr, nc, visit));
        }

        visit[r][c] = false;

        return grid[r][c] + max;
    }
}