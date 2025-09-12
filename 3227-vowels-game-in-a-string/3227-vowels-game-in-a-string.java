class Solution {
    public boolean doesAliceWin(String s) {
        return mySol(s);
    }

    public boolean mySol(String s) {
        int total = 0;

        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) total++;
        }

        if (total == 0) return false;
        if (total % 2 == 1) return true;

        return true;
    }

    private boolean topdown(String s, int total, int alice) {
        if (s.isEmpty()) return alice == 1;

        if (s.length() == total);

        return false;
    }

    private boolean isVowel(char c) {
        return c == 'a' ||
            c == 'e' ||
            c == 'i' ||
            c == 'o' ||
            c == 'u';
    }
}