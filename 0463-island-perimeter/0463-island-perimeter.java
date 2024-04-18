class Solution {
    public int islandPerimeter(int[][] grid) {
        return betterThanMe(grid);
    }

    public int betterThanMe(int[][] grid) {
        int ans = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    ans += 4;

                    if (i > 0 && grid[i - 1][j] == 1) ans -= 2;
                    if (j > 0 && grid[i][j - 1] == 1) ans -= 2;
                }
            }
        }

        return ans;
    }

    public int mySol(int[][] grid) {
        int ans = 0;

        int[][] directions = new int[][] {
            new int[] {-1, 0}
            , new int[] {1, 0}
            , new int[] {0, -1}
            , new int[] {0, 1}
        };

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < directions.length; k++) {
                        int ny = i + directions[k][0];
                        int nx = j + directions[k][1];

                        if (!isIn(grid, ny, nx)) {
                            ans++;
                        } else if (grid[ny][nx] == 0) {
                            ans++;
                        }
                    }

                    grid[i][j]++;
                }
            }
        }

        return ans;
    }

    private boolean isIn(int[][] grid, int y, int x) {
        return !(
            y < 0
            || y >= grid.length
            || x < 0
            || x >= grid[y].length
        );
    }
}