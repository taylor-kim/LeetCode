class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        return mySol(s1, s2);
    }

    public int mySol(String s1, String s2) {
        return topdown(s1, s2, 0, 0, new Integer[s1.length() + 1][s2.length() + 1]);
    }

    public int topdown(String s1, String s2, int i1, int i2, Integer[][] memo) {
        if (i1 >= s1.length() && i2 >= s2.length()) return 0;

        if (i1 >= s1.length()) {
            return memo[i1][i2] = s2.charAt(i2) + topdown(s1, s2, i1, i2 + 1, memo);
        }

        if (i2 >= s2.length()) {
            return memo[i1][i2] = s1.charAt(i1) + topdown(s1, s2, i1 + 1, i2, memo);
        }

        if (memo[i1][i2] != null) return memo[i1][i2];

        if (s1.charAt(i1) == s2.charAt(i2)) {
            return memo[i1][i2] = topdown(s1, s2, i1 + 1, i2 + 1, memo);
        } else {
            int sum1 = s1.charAt(i1) + topdown(s1, s2, i1 + 1, i2, memo);
            int sum2 = s2.charAt(i2) + topdown(s1, s2, i1, i2 + 1, memo);

            return memo[i1][i2] = Math.min(sum1, sum2);
        }
    }
}