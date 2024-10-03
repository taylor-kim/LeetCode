class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        return try_bottomup_opt(s1, s2, s3);
    }

    public boolean try_bottomup_opt(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();

        if (m + n != s3.length()) return false;

        boolean[] dp = new boolean[n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)
                            || dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                }
            }
        }

        return dp[n];
    }

    public boolean try_bottomup2(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();

        if (m + n != s3.length()) return false;

        boolean[][] dp = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)
                            || dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                }
            }
        }

        return dp[m][n];
    }

    public boolean try_bottomup_false(String s1, String s2, String s3) {
        if (s1.length() < s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        int m = s1.length();
        int n = s2.length();

        if (m == 0 || n == 0) {
            return (s1 + s2).equals(s3);
        }

        if (m + n != s3.length()) return false;

        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[m - 1][n] = s1.charAt(m - 1) == s3.charAt(m + n - 1);
        dp[m][n - 1] = s2.charAt(n - 1) == s3.charAt(m + n - 1);

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1.charAt(i) == s3.charAt(i + j)) {
                    dp[i][j] |= dp[i + 1][j];
                }
                if (s2.charAt(j) == s3.charAt(i + j)) {
                    dp[i][j] |= dp[i][j + 1];
                }
            }
        }

        return dp[0][0];
    }

    public boolean mySol(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        
        return mySol(s1, s2, s3, 0, 0, new Boolean[s1.length() + 1][s2.length() + 1]);
    }

    public boolean mySol(String s1, String s2, String s3, int i1, int i2, Boolean[][] memo) {
        if (i1 + i2 >= s3.length()) return true;

        if (memo[i1][i2] != null) {
            return memo[i1][i2];
        }

        boolean ans = false;

        if (i1 < s1.length() && s1.charAt(i1) == s3.charAt(i1 + i2)) {
            ans |= mySol(s1, s2, s3, i1 + 1, i2, memo);
        }

        if (!ans && i2 < s2.length() && s2.charAt(i2) == s3.charAt(i1 + i2)) {
            ans |= mySol(s1, s2, s3, i1, i2 + 1, memo);
        }

        return memo[i1][i2] = ans;
    }
}