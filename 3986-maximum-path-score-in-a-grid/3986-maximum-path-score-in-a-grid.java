class Solution {
    public int maxPathScore(int[][] grid, int k) {
        return mySol(grid, k);
    }

    /**
    [
        [0, 2, 2],
        [1, 1, 1],
        [0, 0, 2]
    ]
    
     */

    public int mySol(int[][] grid, int k) {
        return topdown(grid, 0, 0, k, new Integer[grid.length][grid[0].length][k + 1]);
    }

    public int topdown(int[][] grid, int i, int j, int k, Integer[][][] memo) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return -1;

        int nextK = k - Math.min(grid[i][j], 1);

        if (nextK < 0) return -1;

        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];

        if (memo[i][j][k] != null) return memo[i][j][k];

        int right = topdown(grid, i, j + 1, nextK, memo);
        int down = topdown(grid, i + 1, j, nextK, memo);

        int max = Math.max(right, down);

        return memo[i][j][k] = max < 0 ? -1 : max + grid[i][j];
    }
}