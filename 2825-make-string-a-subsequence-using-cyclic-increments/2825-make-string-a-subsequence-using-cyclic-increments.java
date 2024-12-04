class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        return mySol2(str1, str2);
    }

    public boolean mySol2(String str1, String str2) {
        int i2 = 0;
        
        for (int i = 0; i < str1.length() && i2 < str2.length(); i++) {
            if (canMatched(str1.charAt(i), str2.charAt(i2))) {
                i2++;
            }
        }

        return i2 == str2.length();
    }

    public boolean mySol_mle(String str1, String str2) {
        return topdown(str1, str2, 0, 0, new Boolean[str1.length()][str2.length()]);
    }

    public boolean topdown(String str1, String str2, int i1, int i2, Boolean[][] memo) {
        if (i2 == str2.length()) return true;

        if (i1 == str1.length()) return false;

        if (memo[i1][i2] != null) return memo[i1][i2];

        char c = str1.charAt(i1);

        boolean ans = false;

        if (canMatched(str1.charAt(i1), str2.charAt(i2))) {
            ans = topdown(str1, str2, i1 + 1, i2 + 1, memo);
        }

        ans |= topdown(str1, str2, i1 + 1, i2, memo);

        return memo[i1][i2] = ans;
    }

    private boolean canMatched(char c1, char c2) {
        int i1 = c1 - 'a';
        int i2 = c2 - 'a';

        return i1 == i2 || (i1 + 1) % 26 == i2;
    }
}