class Solution {
    public int[] sumPrefixScores(String[] words) {
        return mySol(words);
    }

    public int[] mySol(String[] words) {
        Trie root = new Trie();

        for (String s : words) root.add(s);

        int[] ans = new int[words.length];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = root.getScores(words[i]);
        }

        return ans;
    }

    private class Trie {
        Trie[] children = new Trie[26];
        int scores = 0;

        public void add(String s) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new Trie();
                }

                t = t.children[c - 'a'];
                t.scores++;
            }
        }

        public int getScores(String s) {
            Trie t = this;
            int total = 0;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    return 0;
                }

                t = t.children[c - 'a'];

                total += t.scores;
            }

            return total;
        }
    }
}