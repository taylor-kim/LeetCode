class Solution {
    public int reverseDegree(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            ans += (i + 1) * (26 - (c - 'a'));
        }

        return ans;
    }

    public int mySol_old(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';

            ans += (i + 1) * (26 - index);
        }

        return ans;
    }
}