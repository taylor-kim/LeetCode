class Solution {
    public int cherryPickup(int[][] grid) {
        return mySol(grid);
    }
    
    public int mySol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        Integer[][][] dp = new Integer[m][n][n];
        return dfs(grid, 0, 0, n - 1, dp);
    }
    
    private int dfs(int[][] grid, int y, int x1, int x2, Integer[][][] dp) {
        if (!isValid(grid, y, x1) || !isValid(grid, y, x2)) {
            return 0;
        }
        
        if (dp[y][x1][x2] != null) {
            return dp[y][x1][x2];
        }
        
        int sum = (x1 == x2) ? grid[y][x1] : grid[y][x1] + grid[y][x2];
        
        int next = 0;
        
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                next = Math.max(next, dfs(grid, y + 1, x1 + i, x2 + j, dp));
            }
        }
        
        return dp[y][x1][x2] = sum + next;
    }
    
    private boolean isValid(int[][] grid, int y, int x) {
        return y >= 0 && x >= 0 && y < grid.length && x < grid[0].length;
    }
}