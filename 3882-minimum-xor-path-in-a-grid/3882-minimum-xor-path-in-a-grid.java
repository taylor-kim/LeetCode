class Solution {
    public int minCost(int[][] grid) {
        return editorial(grid);
    }

    public int editorial(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] dp = new boolean[n][1024];
        boolean[] next = new boolean[1024];

        dp[0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int value = grid[i][j];
                Arrays.fill(next, false);

                for (int k = 0; k < 1024; k++) {
                    if (dp[j][k] || (j > 0 && dp[j - 1][k])) {
                        next[k ^ value] = true;
                    }
                }

                System.arraycopy(next, 0, dp[j], 0, 1024);
            }
        }

        for (int k = 0; k < 1024; k++) {
            if (dp[n - 1][k]) {
                return k;
            }
        }

        return -1;
    }

    public int mySol(int[][] grid) {
        int[] ans = {Integer.MAX_VALUE};

        topdown(grid, 0, 0, 0, ans, new boolean[grid.length][grid[0].length][(int)Math.pow(2, 11) - 1]);

        return ans[0];
    }

    private void topdown(int[][] grid, int y, int x, int xor, int[] ans, boolean[][][] visit) {
        if (y < 0 || y >= grid.length || x < 0 || x >= grid[0].length) return;

        if (y == grid.length - 1 && x == grid[0].length - 1) {
            ans[0] = Math.min(ans[0], xor ^ grid[y][x]);

            return;
        }

        if (visit[y][x][xor]) return;

        topdown(grid, y + 1, x, xor ^ grid[y][x], ans, visit);
        topdown(grid, y, x + 1, xor ^ grid[y][x], ans, visit);
    }
}