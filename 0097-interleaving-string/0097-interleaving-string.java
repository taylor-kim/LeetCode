class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        return try_20230825_dp_opt_space(s1, s2, s3);
    }

    public boolean try_20230825_dp_opt_space(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();

        if (m + n != s3.length()) return false;

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

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

    public boolean try_20230825_dp_others(String s1, String s2, String s3) {
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

    public boolean try_20230825_recur_fail(String s1, String s2, String s3) {
        return try_20230825(s1, s2, s3, 0, 0, new Boolean[s1.length() + 1][s2.length() + 1]);
    }

    public boolean try_20230825(String s1, String s2, String s3, int i1, int i2, Boolean[][] memo) {
        if (i1 == s1.length() && i2 == s2.length()) return i1 + i2 == s3.length();

        if (memo[i1][i2] != null) {
            return memo[i1][i2];
        }

        boolean ans = false;

        if (i1 < s1.length() && i1 + i2 < s3.length() && s1.charAt(i1) == s3.charAt(i1 + i2)) {
            ans |= try_20230825(s1, s2, s3, i1 + 1, i2, memo);
        }

        if (i2 < s2.length() && i1 + i2 < s3.length() && s2.charAt(i2) == s3.charAt(i1 + i2)) {
            ans |= try_20230825(s1, s2, s3, i1, i2 + 1, memo);
        }

        return memo[i1][i2] = ans;
    }


























    public boolean others1DDP(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        boolean[] dp = new boolean[s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                    || (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
            }
        }

        return dp[s2.length()];
    }

    public boolean others2DDP(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                    || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public boolean othersMemo(String s1, String s2, String s3) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];

        return othersMemo(s1, s2, s3, 0, 0, memo);
    }

    public boolean othersMemo(String s1, String s2, String s3, int i1, int i2, int[][] memo) {
        if (i1 == s1.length() && i2 == s2.length() && i1 + i2 == s3.length()) return true;

        if (memo[i1][i2] != 0) {
            return memo[i1][i2] == 1;
        }

        boolean ans = false;

        boolean match1 = i1 < s1.length() && i1 + i2 < s3.length() && s1.charAt(i1) == s3.charAt(i1 + i2);
        boolean match2 = i2 < s2.length() && i1 + i2 < s3.length() && s2.charAt(i2) == s3.charAt(i1 + i2);

        if (match1 && match2) {
            ans |= othersMemo(s1, s2, s3, i1 + 1, i2, memo) || othersMemo(s1, s2, s3, i1, i2 + 1, memo);
        } else if (match1) {
            ans |= othersMemo(s1, s2, s3, i1 + 1, i2, memo);
        } else if (match2) {
            ans |= othersMemo(s1, s2, s3, i1, i2 + 1, memo);
        } else {
            ans = false;
        }

        memo[i1][i2] = ans ? 1 : -1;

        return ans;
    }

    public boolean mySol(String s1, String s2, String s3) {
        if (!checkFreq(s1.toCharArray(), s2.toCharArray(), s3.toCharArray())) {
            return false;
        }

        int[][][] memo = new int[2][101][101];

        return mySol(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0, true, memo)
        || mySol(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0, false, memo);
    }

    public boolean mySol(char[] s1, char[] s2, char[] s3, int i1, int i2, int i3, boolean left, int[][][] memo) {
        if (i1 == s1.length && i2 == s2.length && i3 == s3.length) {
            return true;
        }

        if (memo[left ? 0 : 1][i1][i2] != 0) {
            return memo[left ? 0 : 1][i1][i2] == 1;
        }

        if (left) {
            while (i1 < s1.length && i3 < s3.length && s1[i1] == s3[i3]) {
                i1++;
                i3++;

                if (mySol(s1, s2, s3, i1, i2, i3, !left, memo)) {
                    memo[left ? 0 : 1][i1][i2] = 1;
                    return true;
                }
            }
        } else {
            while (i2 < s2.length && i3 < s3.length && s2[i2] == s3[i3]) {
                i2++;
                i3++;

                if (mySol(s1, s2, s3, i1, i2, i3, !left, memo)) {
                    memo[left ? 0 : 1][i1][i2] = 1;
                    return true;
                }
            }
        }

        memo[left ? 0 : 1][i1][i2] = -1;

        return false;
    }

    public boolean mySol2(String s1, String s2, String s3) {
        if (!checkFreq(s1.toCharArray(), s2.toCharArray(), s3.toCharArray())) {
            return false;
        }

        int[][] memo = new int[101][101];

        return mySol2(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0, memo);
    }

    public boolean mySol2(char[] s1, char[] s2, char[] s3, int i1, int i2, int i3, int[][] memo) {
        if (i1 == s1.length && i2 == s2.length && i3 == s3.length) {
            return true;
        }

        if (memo[i1][i2] != 0) {
            return memo[i1][i2] == 1;
        }

        boolean ans = false;

        if (i1 < s1.length && i3 < s3.length && s1[i1] == s3[i3]) {
            ans |= mySol2(s1, s2, s3, i1 + 1, i2, i3 + 1, memo);
        }

        if (i2 < s2.length && i3 < s3.length && s2[i2] == s3[i3]) {
            ans |= mySol2(s1, s2, s3, i1, i2 + 1, i3 + 1, memo);
        }

        memo[i1][i2] = ans ? 1 : -1;

        return ans;
    }

    private boolean checkFreq(char[] s1, char[] s2, char[] s3) {
        if (s1.length + s2.length != s3.length) {
            return false;
        }

        int[] freq1And2 = new int[26];
        int[] freq3 = new int[26];

        fillFreq(s1, freq1And2);
        fillFreq(s2, freq1And2);
        fillFreq(s3, freq3);

        for (int i = 0; i < 26; i++) {
            if (freq1And2[i] != freq3[i]) {
                return false;
            }
        }

        return true;
    }

    private void fillFreq(char[] s, int[] freq) {
        for (int i = 0; i < s.length; i++) {
            freq[s[i] - 'a']++;
        }
    }
}