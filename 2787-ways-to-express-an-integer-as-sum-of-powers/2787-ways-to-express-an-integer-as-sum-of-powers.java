class Solution {
    public int numberOfWays(int n, int x) {
        return official_bottomup(n, x);
    }

    public int official_bottomup(int n, int x) {
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        int mod = (int)1e9 + 7;

        for (int i = 1; i <= n; i++) {
            int power = (int)Math.pow(i, x);

            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= power) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - power]) % mod;
                }
            }
        }

        return dp[n][n];
    }

    public int mySol(int n, int x) {
        return topdown(n, 1, x, new Integer[n + 1][n + 1]);
    }

    public int topdown(int n, int num, int x, Integer[][] memo) {
        if (n <= 0) return n == 0 ? 1 : 0;
        if (num > n) return 0;

        int mod = (int)1e9 + 7;

        if (memo[n][num] != null) return memo[n][num];

        int include = topdown(n - (int)Math.pow(num, x), num + 1, x, memo) % mod;
        int exclude = topdown(n, num + 1, x, memo) % mod;

        return memo[n][num] = (include + exclude) % mod;
    }
}