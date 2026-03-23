class Solution {
    public int maxProductPath(int[][] grid) {
        return mySol_hold(grid);
    }

    public int mySol3(int[][] grid) {
        long[] ans = {Long.MIN_VALUE};

        backtrack(grid, 1, 0, 0, 0, ans);

        int mod = (int)1e9 + 7;

        return Math.max((int)(ans[0] % mod), -1);
    }

    public void backtrack(int[][] grid, long multi, int size, int y, int x, long[] ans) {
        int m = grid.length;
        int n = grid[0].length;

        if (size == m + n - 1) {
            ans[0] = Math.max(ans[0], multi);

            return;
        }

        if (y >= m || x >= n) return;

        long nextMulti = multi * grid[y][x];

        backtrack(grid, nextMulti, size + 1, y + 1, x, ans);

        backtrack(grid, nextMulti, size + 1, y, x + 1, ans);
    }

    public int mySol2_fail(int[][] grid) {
        int mod = (int)1e9 + 7;
        long ans = topdown(grid, 0, 0);
        return Math.max((int)(ans % mod), -1);
    }

    private long topdown(int[][] grid, int y, int x) {
        if (y == grid.length - 1 && x == grid[0].length - 1) return (long)grid[y][x];

        long ans = Long.MIN_VALUE;

        if (y + 1 < grid.length) {
            ans = grid[y][x] * topdown(grid, y + 1, x);
        }

        if (x + 1 < grid[0].length) {
            ans = Math.max(ans, grid[y][x] * topdown(grid, y, x + 1));
        }

        return ans;
    }

    public int mySol_hold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[][][] dp = new long[2][m + 1][n + 1];

        for (long[][] d2 : dp) {
            Arrays.fill(d2[0], 1);

            for (int i = 0; i < d2.length; i++) {
                d2[i][0] = 1l;
            }
        }

        for (int i = 0; i < m; i++) {
            dp[0][i + 1][1] = dp[0][i][1] * grid[i][0];
            dp[1][i + 1][1] = dp[1][i][1] * grid[i][0];
        }

        for (int j = 0; j < n; j++) {
            dp[0][1][j + 1] = dp[0][1][j] * grid[0][j];
            dp[1][1][j + 1] = dp[1][1][j] * grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int num = grid[i][j];

                long min1 = dp[0][i][j + 1] * num;
                long min2 = dp[0][i + 1][j] * num;
                long max1 = dp[1][i][j + 1] * num;
                long max2 = dp[1][i + 1][j] * num;

                dp[0][i + 1][j + 1] = getMinOrMax(true, min1, min2, max1, max2);
                dp[1][i + 1][j + 1] = getMinOrMax(false, min1, min2, max1, max2);
            }
        }

        // for (long[] row : dp[0]) {
        //     System.out.println(Arrays.toString(row));
        // }

        // System.out.println("");

        // for (long[] row : dp[1]) {
        //     System.out.println(Arrays.toString(row));
        // }

        long max = Math.max(dp[0][m][n], dp[1][m][n]);
        int mod = (int)1e9 + 7;
        
        return Math.max((int)(max % mod), -1);
    }

    private long getMinOrMax(boolean min, long ... nums) {
        long ans = min ? Long.MAX_VALUE : Long.MIN_VALUE;

        for (long num : nums) {
            ans = min ? Math.min(ans, num) : Math.max(ans, num);
        }

        return ans;
    }
}