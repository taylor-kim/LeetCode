class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        return mySol(text, brokenLetters);
    }

    public int mySol(String text, String brokenLetters) {
        int ans = 0;
        char[] arr = brokenLetters.toCharArray();

        for (String word : text.split(" ")) {
            int ok = 1;
            for (char c : arr) {
                if (word.indexOf(c) >= 0) {
                    ok = 0;
                    break;
                }
            }
            ans += ok;
        }

        return ans;
    }
}