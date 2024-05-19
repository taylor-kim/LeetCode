class Solution {
    public int getMaximumGold(int[][] grid) {
        return mySol_topdown(grid);
    }

    private int[][] dirs = {
        {0, 1}
        , {0, -1}
        , {1, 0}
        , {-1, 0}
    };

    public int mySol_topdown(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    ans = Math.max(ans, dfs(grid, i, j, new Integer[m][n], new boolean[m][n]));
                }
            }
        }

        return ans;
    }

    public int dfs(int[][] grid, int r, int c, Integer[][] memo, boolean[][] visit) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return 0;

        if (visit[r][c] || grid[r][c] == 0) return 0;

        visit[r][c] = true;

        // if (memo[r][c] != null) throw new RuntimeException();

        int max = 0;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            max = Math.max(max, dfs(grid, nr, nc, memo, visit));
        }

        visit[r][c] = false;

        return memo[r][c] = grid[r][c] + max;
    }
}