class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        return mySol_trie(sentence, searchWord);
    }

    public int mySol_trie(String sentence, String searchWord) {
        String[] words = sentence.split(" ");

        Trie t = new Trie();

        for (int i = 0; i < words.length; i++) {
            t.add(words[i]);

            if (t.find(searchWord)) {
                return i + 1;
            }
        }

        return -1;
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

    class Trie {
        Trie[] children = new Trie[26];
        
        public void add(String word) {
            Trie t = this;

            for (char c : word.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new Trie();
                }

                t = t.children[c - 'a'];
            }
        }

        public boolean find(String search) {
            Trie t = this;

            for (char c : search.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    return false;
                }

                t = t.children[c - 'a'];
            }

            return true;
        }
    }

}