class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        return mySol(sentence, searchWord);
    }

    public int mySol(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) {
                return i + 1;
            }
        }

        return -1;
    }


}