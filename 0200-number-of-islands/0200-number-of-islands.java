class Solution {
    public int numIslands(char[][] grid) {
        return mySol_bfs(grid);
    }

    public int mySol_bfs(char[][] grid) {
        Queue<int[]> queue = new LinkedList();

        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;

        int[][] directions = {
            {-1, 0}
            , {1, 0}
            , {0, -1}
            , {0, 1}
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    queue.add(new int[] {i, j});
                    grid[i][j] = '2';

                    while (!queue.isEmpty()) {
                        int r = queue.peek()[0];
                        int c = queue.poll()[1];

                        for (int[] d : directions) {
                            int nr = r + d[0];
                            int nc = c + d[1];

                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == '1') {
                                grid[nr][nc] = '2';
                                queue.add(new int[] {nr, nc});
                            }
                        }
                    }

                    ans++;
                }
            }
        }

        return ans;
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