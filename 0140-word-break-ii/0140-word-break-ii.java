class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return mySol(s, wordDict);
    }

    public List<String> mySol(String s, List<String> wordDict) {
        Trie t = new Trie();

        for (String word : wordDict) {
            t.build(word);
        }

        List<String> ans = new ArrayList();

        mySol(0, s, t, ans, new StringBuilder());

        return ans;
    }

    public void mySol(int index, String s, Trie dict, List<String> ans, StringBuilder sb) {
        if (index >= s.length()) {
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }

            ans.add(sb.toString());

            return;
        }

        Trie t = dict;

        while (index < s.length() && t != null) {
            char c = s.charAt(index++);
            t = t.findOne(c);

            if (t != null) {
                sb.append(c);
                if (t.isWord) {
                    int length = sb.length();
                    sb.append(" ");
                    //rec
                    mySol(index, s, dict, ans, sb);
                    sb.setLength(length);
                }
            }
        }
    }

    class Trie {
        private Trie[] words = new Trie[26];
        private boolean isWord = false;

        public void build(String s) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.words[c - 'a'] == null) {
                    t.words[c - 'a'] = new Trie();
                }

                t = t.words[c - 'a'];
            }

            t.isWord = true;
        }

        public boolean find(String s) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                t = t.words[c - 'a'];

                if (t == null) break;
            }

            return t != null && t.isWord;
        }

        public Trie findOne(char c) {
            return this.words[c - 'a'];
        }
    }
}