class Solution {
    public int prefixCount(String[] words, String pref) {
        return mySol_trie(words, pref);
    }

    public int mySol_trie(String[] words, String pref) {
        int ans = 0;

        for (String word : words) {
            Trie trie = new Trie();
            trie.build(word);

            if (trie.find(pref)) ans++;
        }

        return ans;
    }

    class Trie {
        Trie[] children = new Trie[26];

        public void build(String s) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new Trie();
                }

                t = t.children[c - 'a'];
            }
        }

        public boolean find(String s) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    return false;
                }

                t = t.children[c - 'a'];
            }

            return true;
        }
    }

    public int mySol(String[] words, String pref) {
        int ans = 0;

        for (String word : words) {
            if (word.startsWith(pref)) ans++;
        }

        return ans;
    }
}