class Solution {
    public int swimInWater(int[][] grid) {
        return mySol(grid);
    }

    private int[][] dirs = {
        {0, 1}
        , {0, -1}
        , {1, 0}
        , {-1, 0}
    };

    public int mySol(int[][] grid) {
        int n = grid.length;

        return topdown(grid, 0, 0, 0, new Integer[n][n][n * n + 1]);
    }

    public int topdown(int[][] grid, int y, int x, int t, Integer[][][] memo) {
        // System.out.println(String.format("y:%d, x:%d, t:%d", y, x, t));
        if (x == grid.length - 1 && y == grid.length - 1) return t;

        // if (grid[y][x] < 0) return Integer.MAX_VALUE;

        if (memo[y][x][t] != null) return memo[y][x][t];

        t = Math.max(grid[y][x], t);

        grid[y][x] = -(grid[y][x] + 1);

        int ans = Integer.MAX_VALUE;

        for (int[] dir : dirs) {
            int ny = y + dir[0];
            int nx = x + dir[1];

            if (!isValid(grid, ny, nx) || grid[ny][nx] < 0) continue;

            int sub = topdown(grid, ny, nx, Math.max(grid[ny][nx], t), memo);

            ans = Math.min(ans, sub);
        }

        grid[y][x] = -(grid[y][x] + 1);

        return memo[y][x][t] = ans;
    }

    private boolean isValid(int[][] grid, int y, int x) {
        return y >= 0 && x >= 0 && y < grid.length && x < grid[0].length;
    }
}