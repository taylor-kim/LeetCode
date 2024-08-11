class Solution {
    public int minDays(int[][] grid) {
        return mySol(grid);
    }

    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int mySol(int[][] grid) {
        if (count(grid) != 1) return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] deg = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    deg[i][j]++;
                    for (int[] d : dirs) {
                        int ny = i + d[0];
                        int nx = j + d[1];

                        if (isIn(grid, ny, nx) && grid[ny][nx] == 1) {
                            deg[ny][nx]++;
                        }
                    }
                }
            }
        }

        int ans = 0;

        int[] target = null;

        // for (int[] row : deg) {
        //     System.out.println(Arrays.toString(row));
        // }

        while ((target = maxDeg(deg)) != null) {
            int y = target[0];
            int x = target[1];
            deg[y][x] = 0;
            
            for (int[] d : dirs) {
                int ny = y + d[0];
                int nx = x + d[1];
                if (isIn(grid, ny, nx) && grid[ny][nx] == 1) {
                    deg[ny][nx]--;
                }
            }

            ans++;
            grid[y][x] = 0;

            if (count(grid) != 1) return ans;
        }

        return ans;
    }

    private int[] maxDeg(int[][] deg) {
        int max = 0;

        int y = -1;
        int x = -1;

        for (int i = 0; i < deg.length; i++) {
            for (int j = 0; j < deg[0].length; j++) {
                if (max < deg[i][j]) {
                    max = deg[i][j];
                    y = i;
                    x = j;
                }
            }
        }

        if (y == -1) return null;

        return new int[] {y, x};
    }

    private int count(int[][] grid) {
        int result = 0;

        int[][] copy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                copy[i][j] = grid[i][j];
            }
        }

        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
                if (copy[i][j] == 1) {
                    dfs(copy, i, j);
                    result++;
                }
            }
        }

        return result;
    }

    private void dfs(int[][] grid, int i, int j) {
        if (!isIn(grid, i, j) || grid[i][j] != 1) {
            return;
        }

        grid[i][j] = 0;

        for (int[] d : dirs) {
            dfs(grid, i + d[0], j + d[1]);
        }
    }

    private boolean isIn(int[][] grid, int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < grid.length && nx < grid[0].length;
    }
}