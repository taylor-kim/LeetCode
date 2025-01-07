class Solution {
    public List<String> stringMatching(String[] words) {
        return official_trie(words);
    }

    public List<String> official_trie(String[] words) {
        List<String> ans = new ArrayList();

        OfficialTrie root = new OfficialTrie();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                root.build(word.substring(i));
            }
        }

        for (String word : words) {
            if (root.isSubString(word)) {
                ans.add(word);
            }
        }

        return ans;
    }

    public class OfficialTrie {
        OfficialTrie[] children = new OfficialTrie[26];
        int freq = 0;

        public void build(String s) {
            OfficialTrie t = this;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new OfficialTrie();
                }

                t = t.children[c - 'a'];
                t.freq++;
            }
        }

        public boolean isSubString(String s) {
            OfficialTrie t = this;

            for (char c : s.toCharArray()) {
                t = t.children[c - 'a'];
            }

            return t.freq > 1;
        }
    }

    public List<String> bruteForce(String[] words) {
        List<String> ans = new ArrayList();

        Arrays.sort(words, (a, b) -> {
            return a.length() != b.length() ? a.length() - b.length() : a.compareTo(b);
        });

        for (int i = 0; i < words.length; i++) {
            String a = words[i];
            for (int j = i + 1; j < words.length; j++) {
                String b = words[j];

                if (!a.equals(b) && b.contains(a)) {
                    ans.add(a);
                    break;
                }
            }
        }

        return ans;
    }

    public List<String> mySol(String[] words) {
        Trie root = new Trie(0);

        for (String s : words) root.build(s);

        List<String> ans = new ArrayList();

        for (String s : words) {
            if (isSubString(s, root)) {
                ans.add(s);
            }
        }

        return ans;
    }

    private boolean isSubString(String s, Trie t) {
        if (t == null) return false;

        Trie found = t.find(s);

        if (found != null && (found.length != s.length() || found.hasNext())) {
            return true;
        }

        for (Trie next : t.children) {
            if (isSubString(s, next)) {
                return true;
            }
        }

        return false;
    }

    class Trie {
        Trie[] children = new Trie[26];
        int length = 0;
        boolean isWord = false;

        Trie(int length) {
            this.length = length;
        }

        public boolean hasNext() {
            for (Trie next : children) {
                if (next != null) return true;
            }

            return false;
        }

        public String getString() {
            return String.format("length:%d, isWord:%b, children:%s", length, isWord, Arrays.toString(children));
        }

        public void build(String s) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new Trie(t.length + 1);
                }

                t = t.children[c - 'a'];
            }

            t.isWord = true;
        }

        public Trie find(String s) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    return null;
                }

                t = t.children[c - 'a'];
            }

            return t;
        }
    }
}