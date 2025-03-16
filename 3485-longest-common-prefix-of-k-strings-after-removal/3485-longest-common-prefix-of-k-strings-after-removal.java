class Solution {
    public int[] longestCommonPrefix(String[] words, int k) {
        return others(words, k);
    }

    public int[] others(String[] words, int k) {
        TreeMap<Integer, Integer> map = new TreeMap();

        Trie trie = new Trie();

        for (String word : words) trie.add(word, k, map);

        int[] ans = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            trie.remove(words[i], k, map);
            ans[i] = map.isEmpty() ? 0 : map.lastKey();
            trie.add(words[i], k, map);
        }

        return ans;
    }

    class Trie {
        Trie[] children = new Trie[26];
        int count = 0;

        public void add(String s, int k, Map<Integer, Integer> map) {
            Trie node = this;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Trie();
                }

                node = node.children[c - 'a'];
                node.count++;

                if (node.count == k) {
                    map.put(i + 1, map.getOrDefault(i + 1, 0) + 1);
                }
            }
        }

        public void remove(String s, int k, Map<Integer, Integer> map) {
            Trie node = this;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                node = node.children[c - 'a'];

                if (node.count == k) {
                    map.put(i + 1, map.get(i + 1) - 1);

                    if (map.get(i + 1) == 0) {
                        map.remove(i + 1);
                    }
                }

                node.count--;
            }
        }
    }

    public int[] mySol(String[] words, int k) {
        int n = words.length;
        Map<String, Integer> lcpMap = new HashMap();

        for (String word : words) {
            for (int i = word.length(); i > 0; i--) {
                String prefix = word.substring(0, i);
                lcpMap.put(prefix, lcpMap.getOrDefault(prefix, 0) + 1);
            }
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            String remove = words[i];
        }

        return ans;
    }
}