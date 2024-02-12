class Solution {
    public int cherryPickup(int[][] grid) {
        return try_bottomup_spaceopt2(grid);
    }
    
    public int try_bottomup_spaceopt2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][][] dp = new int[2][n + 1][n + 1];
        
        for (int r = m - 1; r >= 0; r--) {
            for (int c1 = 0; c1 <= n / 2 + 1; c1 ++) {
                for (int c2 = n - 1; c2 > c1; c2--) {
                    int sum = 0;
                    
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (c1 + i >= 0 && c2 + j >= 0 && c1 + i != c2 + j) {
                                int relIndex = c2 + j - (n / 2 + 2);
                                sum = Math.max(sum, dp[(r + 1) % 2][c1 + i][c2 + j]);
                            }
                        }
                    }
                    
                    dp[r % 2][c1][c2] = sum + grid[r][c1] + grid[r][c2];
                }
            }
        }
        
        return dp[0][0][n - 1];
    }
    
    public int try_bottomup_spaceopt(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[n + 1][n + 1];
        
        for (int r = m - 1; r >= 0; r--) {
            int[][] currentDp = new int[n + 1][n + 1];
            for (int c1 = 0; c1 < n; c1 ++) {
                for (int c2 = n - 1; c2 > c1; c2--) {
                    int sum = 0;
                    
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (c1 + i >= 0 && c2 + j >= 0) {
                                sum = Math.max(sum, dp[c1 + i][c2 + j]);
                            }
                        }
                    }
                    
                    currentDp[c1][c2] = sum + grid[r][c1] + grid[r][c2];
                }
            }
            dp = currentDp;
        }
        
        return dp[0][n - 1];
    }
    
    public int try_bottomup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][][] dp = new int[m + 1][n + 1][n + 1];
        
        for (int r = m - 1; r >= 0; r--) {
            for (int c1 = 0; c1 < n; c1 ++) {
                // for (int c2 = n - 1; c2 >= 0; c2--) {
                for (int c2 = n - 1; c2 > c1; c2--) {
                    int sum = 0;
                    
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (c1 + i >= 0 && c2 + j >= 0) {
                                sum = Math.max(sum, dp[r + 1][c1 + i][c2 + j]);
                            }
                        }
                    }
                    
                    // dp[r][c1][c2] = sum + (c1 == c2 ? grid[r][c1] : grid[r][c1] + grid[r][c2]);
                    dp[r][c1][c2] = sum + grid[r][c1] + grid[r][c2];
                }
            }
        }
        
        return dp[0][0][n - 1];
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