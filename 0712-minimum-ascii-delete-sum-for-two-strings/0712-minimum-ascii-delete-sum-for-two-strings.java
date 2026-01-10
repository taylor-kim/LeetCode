class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        return mySol2_bottomup(s1, s2);
    }

    public int mySol2_bottomup(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] dp = new int[c1.length + 1][c2.length + 1];

        for (int[] row : dp) {
            Arrays.fill(row, 1000 * ((int)'z' + 1));
        }

        dp[c1.length][c2.length] = 0;

        for (int i1 = c1.length; i1 >= 0; i1--) {
            for (int i2 = c2.length; i2 >= 0; i2--) {
                if (i1 >= c1.length && i2 >= c2.length) {
                    continue;
                } else if (i1 >= c1.length) {
                    dp[i1][i2] = c2[i2] + dp[i1][i2 + 1];
                } else if (i2 >= c2.length) {
                    dp[i1][i2] = c1[i1] + dp[i1 + 1][i2];
                } else if (c1[i1] == c2[i2]) {
                    dp[i1][i2] = dp[i1 + 1][i2 + 1];
                } else {
                    dp[i1][i2] = Math.min(
                        c1[i1] + dp[i1 + 1][i2],
                        c2[i2] + dp[i1][i2 + 1]
                    );
                }
            }
        }

        return dp[0][0];
    }

    public int mySol(String s1, String s2) {
        return topdown(s1.toCharArray(), s2.toCharArray(), 0, 0, new Integer[s1.length() + 1][s2.length() + 1]);
    }

    public int topdown(char[] s1, char[] s2, int i1, int i2, Integer[][] memo) {
        if (i1 >= s1.length && i2 >= s2.length) return 0;

        if (memo[i1][i2] != null) return memo[i1][i2];

        if (i1 >= s1.length) {
            return memo[i1][i2] = s2[i2] + topdown(s1, s2, i1, i2 + 1, memo);
        }

        if (i2 >= s2.length) {
            return memo[i1][i2] = s1[i1] + topdown(s1, s2, i1 + 1, i2, memo);
        }

        if (s1[i1] == s2[i2]) {
            return memo[i1][i2] = topdown(s1, s2, i1 + 1, i2 + 1, memo);
        } else {
            int sum1 = s1[i1] + topdown(s1, s2, i1 + 1, i2, memo);
            int sum2 = s2[i2] + topdown(s1, s2, i1, i2 + 1, memo);

            return memo[i1][i2] = Math.min(sum1, sum2);
        }
    }
}