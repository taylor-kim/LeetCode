class Solution {
    public int[][] findFarmland(int[][] land) {
        return official_greedy(land);
    }

    int[][] dir = {
        {-1, 0}, {0, -1}, {0, 1}, {1, 0}
    };

    public int[][] official_greedy(int[][] land) {
        int m = land.length;
        int n = land[0].length;

        List<int[]> list = new ArrayList();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    int r2, c2 = 0;
                    for (r2 = i; r2 < m && land[r2][j] == 1; r2++) {
                        for (c2 = j; c2 < n && land[r2][c2] == 1; c2++) {
                            land[r2][c2] = 2;
                        }
                    }

                    list.add(new int[] {i, j, r2 - 1, c2 - 1});
                }
            }
        }

        return list.stream().toArray(int[][]::new);
    }

    public int[][] official_bfs(int[][] land) {
        List<int[]> list = new ArrayList();

        Queue<int[]> queue = new LinkedList();
        
        int m = land.length;
        int n = land[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    queue.add(new int[] {i, j});
                    land[i][j] = 0;
                    int[] last = null;
                    
                    while (!queue.isEmpty()) {
                        last = queue.poll();
                        int y = last[0];
                        int x = last[1];

                        for (int[] d : dir) {
                            int ny = y + d[0];
                            int nx = x + d[1];

                            if (ny >= 0 && ny < land.length && nx >= 0 && nx < land[0].length && land[ny][nx] == 1) {
                                queue.add(new int[] {ny, nx});
                                land[ny][nx] = 0;
                            }
                        }
                    }

                    list.add(new int[] {i, j, last[0], last[1]});
                }
            }
        }

        return list.stream().toArray(int[][]::new);
    }

    public int[][] mySol_dfs(int[][] land) {
        List<int[]> list = new ArrayList();

        int m = land.length;
        int n = land[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    list.add(new int[] {i, j, i, j});
                    dfs(land, i, j, list);
                }
            }
        }

        int[][] ans = new int[list.size()][];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private void dfs(int[][ ]land, int r, int c, List<int[]> list) {
        if (r < 0 || r >= land.length || c < 0 || c >= land[0].length) return;

        if (land[r][c] != 1) return;

        land[r][c] = 2;

        int[] pos = list.get(list.size() - 1);

        // pos[0] = Math.min(pos[0], r);
        // pos[1] = Math.min(pos[1], c);

        pos[2] = Math.max(pos[2], r);
        pos[3] = Math.max(pos[3], c);

        dfs(land, r - 1, c, list);
        dfs(land, r + 1, c, list);
        dfs(land, r, c - 1, list);
        dfs(land, r, c + 1, list);
    }

    private boolean isIn(int[][] grid, int y, int x) {
        return y >= 0 && y < grid.length && x >= 0 && x < grid[0].length;
    }
}