class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        Trie trie = new Trie();

        for (String word : dictionary) {
            trie.append(word);
        }

        List<String> list = new ArrayList();

        for (String query : queries) {
            if (isMatched(query, 0, 2, trie)) list.add(query);
        }

        return list;
    }

    private boolean isMatched(String query, int index, int mod, Trie trie) {
        if (index >= query.length()) return true;

        int c = query.charAt(index) - 'a';

        if (trie.letters[c] != null) {
            if (isMatched(query, index + 1, mod, trie.letters[c])) {
                return true;
            }
        }
        
        if (mod > 0) {
            for (Trie next : trie.letters) {
                if (next != null) {
                    boolean matched = isMatched(query, index + 1, mod - 1, next);

                    if (matched) return true;
                }
            }
        }

        return false;
    }

    public class Trie {
        Trie[] letters = new Trie[26];
        private boolean isWord = false;

        public void append(String word) {
            Trie t = this;

            for (char c : word.toCharArray()) {
                if (t.letters[c - 'a'] == null) {
                    t.letters[c - 'a'] = new Trie();
                }

                t = t.letters[c - 'a'];
            }

            t.isWord = true;
        }

        public boolean find(String word) {
            Trie t = this;

            for (char c : word.toCharArray()) {
                if (t.letters[c - 'a'] == null) {
                    return false;
                }

                t = t.letters[c - 'a'];
            }

            return t.isWord;
        }
    }
}