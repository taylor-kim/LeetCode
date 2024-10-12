class Solution {
    public int uniquePaths(int m, int n) {
        return try_20230903_dp_opt_space(m, n);
    }

    public int try_20230903_dp_opt_space(int m, int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                dp[x + 1] = dp[x + 1] + dp[x];
            }
        }

        return dp[n];
    }

    public int try_20230903_dp(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[m - 1][n - 1] = 1;

        for (int y = m - 1; y >= 0; y--) {
            for (int x = n - 1; x >= 0; x--) {
                if (y == m -1 && x == n - 1) continue;
                dp[y][x] = dp[y + 1][x] + dp[y][x + 1];
            }
        }

        return dp[0][0];
    }

    public int try_20230903_rec(int m, int n) {
        return try_20230903_rec(m, n, 0, 0, new Integer[m][n]);
    }

    public int try_20230903_rec(int m, int n, int y, int x, Integer[][] memo) {
        if (y == m - 1 && x == n - 1) return 1;

        if (y >= m || x >= n) return 0;

        if (memo[y][x] != null) {
            return memo[y][x];
        }

        int right = try_20230903_rec(m, n, y, x + 1, memo);
        int down = try_20230903_rec(m, n, y + 1, x, memo);

        return memo[y][x] = right + down;
    }

    public int tryAgainDP(int m, int n) {
        int[][] dp = new int[m][n];

        Arrays.fill(dp[0], 1);

        for (int row = 0; row < m; row++) {
            dp[row][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public int tryAgainRec(int m, int n) {
        int[][] grid = new int[m][n];

        int ans = tryAgainRec(0, 0, grid);

        // for (int i = 0; i < m; i++) {
        //     System.out.println(Arrays.toString(grid[i]));
        // }

        return ans;
    }

    public int tryAgainRec(int y, int x, int[][] grid) {
        if (y < 0 || y >= grid.length || x < 0 || x >= grid[y].length) {
            return 0;
        }

        if (y == grid.length - 1 && x == grid[y].length - 1) {
            return 1;
        }

        if (grid[y][x] == 0) {
            grid[y][x] = tryAgainRec(y + 1, x, grid) + tryAgainRec(y, x + 1, grid);
        }

        return grid[y][x];
    }

    public int doLoop(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public int doRecursive(int m, int n) {
        int[][] memo = new int[m][n];

        int ans = doRecursive(m, n, 0, 0, memo);

        return ans;
    }

    public int doRecursive(int m, int n, int y, int x, int[][] memo) {
        if (y == m - 1 && x == n - 1) {
            return 1;
        }

        if (memo[y][x] != 0) {
            return memo[y][x];
        }

        int ans = 0;

        if (y + 1 < m) {
            ans += doRecursive(m, n, y + 1, x, memo);
        }

        if (x + 1 < n) {
            ans += doRecursive(m, n, y, x + 1, memo);
        }

        memo[y][x] = ans;

        return ans;
    }
}