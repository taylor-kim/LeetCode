class Solution {
    public int islandPerimeter(int[][] grid) {
        return others_nice(grid);
    }

    public int others_nice(int[][] grid) {
        int ans = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    ans += 4;

                    if (r > 0 && grid[r - 1][c] == 1) ans -= 2;
                    if (c > 0 && grid[r][c - 1] == 1) ans -= 2;
                }
            }
        }

        return ans;
    }

    public int myPast_sol2(int[][] grid) {
        int ans = 0;

        int[][] directions = {
            {-1, 0}
            , {1, 0}
            , {0, -1}
            , {0, 1}
        };

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    for (int[] d : directions) {
                        int nr = r + d[0];
                        int nc = c + d[1];

                        if (!isIn(grid, nr, nc)) {
                            ans++;
                        } else if (grid[nr][nc] == 0) {
                            ans++;
                        }
                    }
                }
            }
        }

        return ans;
    }

    private boolean isIn(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }

    public int myPast_sol(int[][] grid) {
        int ans = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    if (r == 0 || grid[r - 1][c] == 0) ans++;
                    if (r == grid.length - 1 || grid[r + 1][c] == 0) ans++;

                    if (c == 0 || grid[r][c - 1] == 0) ans++;
                    if (c == grid[0].length - 1 || grid[r][c + 1] == 0) ans++;
                }
            }
        }

        return ans;
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