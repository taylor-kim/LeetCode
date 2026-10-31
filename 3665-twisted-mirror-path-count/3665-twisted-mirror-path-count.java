class Solution {
    public int uniquePaths(int[][] grid) {
        return mySol(grid);
    }

    public int mySol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        return topdown(grid, 0, 0, 0, new Integer[2][m][n]);
    }

    public int topdown(int[][] grid, int dir, int y, int x, Integer[][][] memo) {
        if (y < 0 || y >= grid.length || x < 0 || x >= grid[0].length) return 0;

        if (y == grid.length - 1 && x == grid[0].length - 1) return 1;

        if (memo[dir][y][x] != null) return memo[dir][y][x];

        int mod = (int)1e9 + 7;

        if (grid[y][x] == 0) {
            memo[dir][y][x] = (topdown(grid, 1, y + 1, x, memo) + topdown(grid, 0, y, x + 1, memo)) % mod;
        } else if (dir == 0) {
            memo[dir][y][x] = topdown(grid, 1, y + 1, x, memo);
        } else {
            memo[dir][y][x] = topdown(grid, 0, y, x + 1, memo);
        }

        return memo[dir][y][x];
    }
}