class Solution {
    public int numDistinct(String s, String t) {
        return mySol(s, t);
    }

    public int mySol(String s, String t) {
        // return topdown(s, t, 0, 0, new Integer[s.length()][t.length()]);
        return try_bottomup_space_opt(s, t);
    }

    public int try_bottomup_space_opt(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[] dp = new int[n + 1];

        dp[n] = 1;

        for (int i = m - 1; i >= 0; i--) {
            char c1 = s.charAt(i);
            int nextJ = 1;
            for (int j = n - 1; j >= 0; j--) {
                char c2 = t.charAt(j);

                int temp = dp[j];

                if (c1 == c2) {
                    dp[j] += nextJ;
                }

                nextJ = temp;
            }
        }

        return dp[0];
    }

    public int try_bottomup(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }

        for (int i = m - 1; i >= 0; i--) {
            char c1 = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char c2 = t.charAt(j);

                dp[i][j] = dp[i + 1][j];

                if (c1 == c2) {
                    dp[i][j] += dp[i + 1][j + 1];
                }
            }
        }

        return dp[0][0];
    }

    public int topdown(String s, String t, int i, int j, Integer[][] memo) {
        if (j == t.length()) return 1;

        if (i >= s.length()) return 0;

        if (memo[i][j] != null) return memo[i][j];

        int exclude = topdown(s, t, i + 1, j, memo);
        int include = 0;

        if (s.charAt(i) == t.charAt(j)) {
            include = topdown(s, t, i + 1, j + 1, memo);
        }

        return memo[i][j] = include + exclude;
    }
}