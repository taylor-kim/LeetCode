class Solution {
    public int numberOfSpecialChars(String word) {
        return mySol(word);
    }

    public int mySol(String word) {
        int[] lowers = new int[26];
        int[] uppers = new int[26];

        for (char c : word.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lowers[c - 'a']++;
            } else {
                uppers[c - 'A']++;
            }
        }

        int ans = 0;

        for (int i = 0; i < 26; i++) {
            if (lowers[i] > 0 && uppers[i] > 0) {
                ans++;
            }
        }

        return ans;
    }
}