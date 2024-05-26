class Solution {
    public int checkRecord(int n) {
        return official_bottomup(n);
    }

    public int try_spaceopt_official_bottomup(int n) {
        int[][][] dp = new int[n + 1][2][3];
        int mod = (int)1e9 + 7;

        for (int i = 0; i < n; i++) {
            for (int a = 0; a <= 1; a++) {
                for (int l = 0; l <= 2; l++) {
                    dp[i + 1][a][0] = (
                        dp[i + 1][a][0] +
                        dp[i][a][l]
                    ) % mod;

                    if (a < 1) {
                        dp[i + 1][a + 1][0] = (
                            dp[i + 1][a + 1][0] +
                            dp[i][a][l]
                        ) % mod;
                    }

                    if (l < 2) {
                        dp[i + 1][a][l + 1] = (
                            dp[i + 1][a][l + 1] +
                            dp[i][a][l]
                        ) % mod;
                    }
                }
            }
        }

        int ans = 0;

        for (int a = 0; a < 2; a++) {
            for (int l = 0; l < 3; l++) {
                ans = (ans + dp[n][a][l]) % mod;
            }
        }

        return ans;
    }

    public int official_bottomup(int n) {
        int[][][] dp = new int[n + 1][2][3];
        dp[0][0][0] = 1;
        int mod = (int)1e9 + 7;

        for (int i = 0; i < n; i++) {
            for (int a = 0; a <= 1; a++) {
                for (int l = 0; l <= 2; l++) {
                    dp[i + 1][a][0] = (
                        dp[i + 1][a][0] +
                        dp[i][a][l]
                    ) % mod;

                    if (a < 1) {
                        dp[i + 1][a + 1][0] = (
                            dp[i + 1][a + 1][0] +
                            dp[i][a][l]
                        ) % mod;
                    }

                    if (l < 2) {
                        dp[i + 1][a][l + 1] = (
                            dp[i + 1][a][l + 1] +
                            dp[i][a][l]
                        ) % mod;
                    }
                }
            }
        }

        int ans = 0;

        for (int a = 0; a < 2; a++) {
            for (int l = 0; l < 3; l++) {
                ans = (ans + dp[n][a][l]) % mod;
            }
        }

        return ans;
    }

    public int official_topdown(int n) {
        return official_topdown(n, 0, 0, new Integer[n + 1][2][3]);
    }

    public int official_topdown(int n, int a, int l, Integer[][][] memo) {
        if (a >= 2 || l >= 3) return 0;

        if (n == 0) {
            return 1;
        }

        if (memo[n][a][l] != null) {
            return memo[n][a][l];
        }

        int mod = (int)1e9 + 7;

        long ans = official_topdown(n - 1, a + 1, 0, memo) % mod;

        ans = (official_topdown(n - 1, a, l + 1, memo) + ans) % mod;

        ans = (official_topdown(n - 1, a, 0, memo) + ans) % mod;

        return memo[n][a][l] = (int)ans;
    }

    public int try_bottomup_fail(int n) {
        int mod = (int)1e9 + 7;

        long[][][] dp = new long[n + 1][4][5];

        for (int a = 0; a < 3; a++) {
            for (int l = 0; l < 4; l++) {
                dp[n][a][l] = 1;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int a = 0; a < 3; a++) {
                for (int l = 0; l < 4; l++) {
                    dp[i][a][l] = (dp[i][a][l] + dp[i + 1][a][l]) % mod;
                }
            }
        }

        return (int)dp[0][0][0];
    }

    public int mySol(int n) {
        return mySol(n, 0, 0, 0, new Integer[n][2][3]);
    }

    public int mySol(int n, int index, int a, int l, Integer[][][] memo) {
        if (a >= 2) return 0;

        if (l >= 3) return 0;

        if (index >= n) {
            return 1;
        }

        if (memo[index][a][l] != null) {
            return memo[index][a][l];
        }

        int mod = (int)1e9 + 7;

        long ans = mySol(n, index + 1, a + 1, 0, memo) % mod;

        ans = (mySol(n, index + 1, a, l + 1, memo) + ans) % mod;

        ans = (mySol(n, index + 1, a, 0, memo) + ans) % mod;

        return memo[index][a][l] = (int)ans;
    }
}