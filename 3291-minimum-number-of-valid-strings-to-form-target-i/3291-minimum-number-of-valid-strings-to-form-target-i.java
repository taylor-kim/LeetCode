class Solution {
    public int minValidStrings(String[] words, String target) {
        return mySol3(words, target);
    }

    public int mySol3(String[] words, String target) {
        Trie dict = new Trie();

        for (String word : words) {
            dict.build(word);
        }

        return topdown2(0, target, dict, new Integer[target.length()]);
    }

    private int topdown2(int index, String target, Trie root, Integer[] memo) {
        if (index >= target.length()) return 0;

        if (memo[index] != null) {
            return memo[index];
        }

        int ans = Integer.MAX_VALUE;

        Trie t = root;

        for (int i = index; i < target.length(); i++) {
            if (t.children[target.charAt(i) - 'a'] != null) {
                int sub = topdown2(i + 1, target, root, memo);

                if (sub >= 0) {
                    ans = Math.min(ans, 1 + sub);
                }

                t = t.children[target.charAt(i) - 'a'];
            } else {
                break;
            }
        }

        return memo[index] = (ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public int mySol2_fail(String[] words, String target) {
        Set<String> prefixSet = new HashSet();

        for (String word : words) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                sb.append(word.charAt(i));
                prefixSet.add(sb.toString());
            }
        }

        return find(target, prefixSet);
    }

    private int find(String target, Set<String> prefixSet) {
        if (prefixSet.contains(target)) return 1;

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < target.length(); i++) {
            String left = target.substring(0, i);

            int l = find(left, prefixSet);

            String right = target.substring(i, target.length());

            int r = find(right, prefixSet);

            int min = Math.min(l, r);

            ans = Math.min(ans, min);
        }

        return ans;
    }

    public int mySol_fail(String[] words, String target) {
        Trie dict = new Trie();

        for (String word : words) {
            dict.build(word);
        }

        return topdown(0, target, dict, null, new Integer[target.length()]);
    }

    private int topdown(int index, String target, Trie root, Trie t, Integer[] memo) {
        if (index >= target.length()) return 0;

        if (memo[index] != null) {
            return memo[index];
        }

        int ans = Integer.MAX_VALUE;

        char c = target.charAt(index);

        if (root.children[c - 'a'] != null) {
            int sub = topdown(index + 1, target, root, root.children[c - 'a'], memo);

            if (sub >= 0) {
                ans = Math.min(ans, 1 + sub);
            }
        }

        if (t != null && t.children[c - 'a'] != null) {
            int sub = topdown(index + 1, target, root, t.children[c - 'a'], memo);

            if (sub >= 0) {
                ans = Math.min(ans, sub);
            }
        }

        return memo[index] = (ans == Integer.MAX_VALUE ? -1 : ans);
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
    }
}