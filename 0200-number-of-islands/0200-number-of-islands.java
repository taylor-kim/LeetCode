class Solution {
    public int numIslands(char[][] grid) {
        return tryAgain_20230529(grid);
    }

    public int tryAgain_20230529(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs_20230529(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    public void dfs_20230529(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;

        if (grid[i][j] != '1') return;

        grid[i][j] = '2';

        dfs_20230529(grid, i + 1, j);
        dfs_20230529(grid, i - 1, j);
        dfs_20230529(grid, i, j + 1);
        dfs_20230529(grid, i, j - 1);
    }



























    public int tryAgain(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int y, int x) {
        if (y < 0 || x < 0 || y >= grid.length || x >= grid[y].length) {
            return;
        }

        if (grid[y][x] == '1') {
            grid[y][x] = '2';

            dfs(grid, y - 1, x);
            dfs(grid, y + 1, x);
            dfs(grid, y, x - 1);
            dfs(grid, y, x + 1);
        }
    }

    public int mySol(char[][] grid) {
        int groupNumber = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    doWithRecursion(grid, i, j);

                    groupNumber++;
                }
            }
        }

        return groupNumber;
    }

    private void doWithRecursion(char[][] grid, int y, int x) {
        if (y < 0 || x < 0 || y >= grid.length || x >= grid[0].length) {
            return;
        }

        if (grid[y][x] == '1') {
            grid[y][x] = '2';
            doWithRecursion(grid, y - 1, x);
            doWithRecursion(grid, y + 1, x);
            doWithRecursion(grid, y, x - 1);
            doWithRecursion(grid, y, x + 1);
        }        
    }
}