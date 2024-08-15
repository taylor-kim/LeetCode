class Solution {
    public int minDays(int[][] grid) {
        return official_bruteforce(grid);
    }

    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int official_bruteforce(int[][] grid) {
        if (count(grid) != 1) return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;

                    if (count(grid) != 1) {
                        return 1;
                    }

                    grid[i][j] = 1;
                }
            }
        }

        return 2;
    }

    private int count(int[][] grid) {
        int result = 0;
        boolean[][] visit = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visit[i][j] && grid[i][j] == 1) {
                    dfs(grid, i, j, visit);
                    result++;
                }
            }
        }

        return result;
    }

    private void dfs(int[][] grid, int i, int j, boolean[][] visit) {
        if (!isIn(grid, i, j) || grid[i][j] != 1 || visit[i][j]) {
            return;
        }

        visit[i][j] = true;

        for (int[] d : dirs) {
            dfs(grid, i + d[0], j + d[1], visit);
        }
    }

    private boolean isIn(int[][] grid, int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < grid.length && nx < grid[0].length;
    }
}