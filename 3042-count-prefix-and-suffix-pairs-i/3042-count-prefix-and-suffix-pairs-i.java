class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        return official_dual_trie(words);
    }

    public int official_dual_trie(String[] words) {
        int ans = 0;

        for (int i = 0; i < words.length; i++) {
            String a = words[i];

            Trie prefix = new Trie();
            Trie suffix = new Trie();

            prefix.build(a);
            suffix.build(new StringBuilder(a).reverse().toString());

            for (int j = 0; j < i; j++) {
                String b = words[j];

                if (a.length() >= b.length() 
                    && prefix.find(b)
                    && suffix.find(new StringBuilder(b).reverse().toString())) {
                    ans++;
                }
            }
        }

        return ans;
    }

    class Trie {
        Trie[] children = new Trie[26];
        boolean isWord = false;

        public void build(String s) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new Trie();
                }

                t = t.children[c - 'a'];
            }

            t.isWord = true;
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

    public int mySol(String[] words) {
        int ans = 0;

        for (int i = 0; i < words.length; i++) {
            String a = words[i];
            for (int j = i + 1; j < words.length; j++) {
                String b = words[j];

                if (a.equals(b) || (b.startsWith(a) && b.endsWith(a))) {
                    ans++;
                }
            }
        }

        return ans;
    }
}