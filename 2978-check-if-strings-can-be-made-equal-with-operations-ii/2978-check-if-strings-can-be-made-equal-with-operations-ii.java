class Solution {
    public boolean checkStrings(String s1, String s2) {
        return mySol(s1, s2);
    }

    public boolean mySol(String s1, String s2) {
        int n = s1.length();
        int[][] freqs = new int[2][26];

        for (int i = 0; i < n; i++) {
            freqs[i % 2][s1.charAt(i) - 'a']++;
            freqs[i % 2][s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (freqs[0][i] != 0 || freqs[1][i] != 0) return false;
        }

        return true;
    }
}