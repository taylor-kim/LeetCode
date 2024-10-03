class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        return mySol(s1, s2, s3);
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