class Solution {
    public boolean isCircularSentence(String sentence) {
        return mySol(sentence);
    }

    public boolean mySol(String sentence) {
        String[] words = sentence.split(" ");
        int n = words.length;

        for (int i = 0; i < n; i++) {
            char c1 = words[i].charAt(words[i].length() - 1);
            char c2 = words[(i + 1) % n].charAt(0);

            if (c1 != c2) return false;
        }

        return true;
    }
}