class Solution {
    public int numberOfSpecialChars(String word) {
        return mySol(word);
    }

    public int mySol(String word) {
        int[] lowerLast = new int[26];
        int[] upperFirst = new int[26];

        Arrays.fill(lowerLast, -1);
        Arrays.fill(upperFirst, -1);

        int ans = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (Character.isLowerCase(c)) {
                lowerLast[c - 'a'] = i;
            } else if (upperFirst[c - 'A'] == -1) {
                upperFirst[c - 'A'] = i;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (lowerLast[i] != -1 && lowerLast[i] < upperFirst[i]) {
                ans++;
            }
        }

        return ans;
    }
}