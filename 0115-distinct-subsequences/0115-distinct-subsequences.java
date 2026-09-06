class Solution {
    public int numDistinct(String s, String t) {
        return mySol(s, t);
    }

    public int mySol(String s, String t) {
        return topdown(s, t, 0, 0, new Integer[s.length()][t.length()]);
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