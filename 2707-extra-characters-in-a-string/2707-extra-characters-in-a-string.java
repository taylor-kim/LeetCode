class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        return mySol_trie(s, dictionary);
    }

    public int mySol_trie(String s, String[] dictionary) {
        Trie dict = new Trie();

        for (String word : dictionary) {
            dict.add(word);
        }

        return mySol_trie(s, dict, 0, new Integer[s.length()]);
    }

    public int mySol_trie(String s, Trie dict, int index, Integer[] memo) {
        if (index >= s.length()) return 0;

        if (memo[index] != null) {
            return memo[index];
        }

        int ans = 1 + mySol_trie(s, dict, index + 1, memo);

        Trie trie = dict;

        for (int i = index; i < s.length(); i++) {
            trie = trie.children[s.charAt(i) - 'a'];

            if (trie == null) break;

            if (trie.isWord) {
                ans = Math.min(ans, mySol_trie(s, trie, i + 1, memo));
            }
        }

        return memo[index] = ans;
    }

    public int bottomup2(String s, String[] dictionary) {
        Trie root = new Trie();

        for (String word : dictionary) {
            root.add(word);
        }

        int n = s.length();

        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            Trie trie = root;

            dp[i] = 1 + dp[i + 1];

            for (int j = i; j < n; j++) {
                trie = trie.children[s.charAt(j) - 'a'];

                if (trie == null) break;

                if (trie.isWord) {
                    dp[i] = Math.min(dp[i], dp[j + 1]);
                }
            }
        }

        return dp[0];
    }

    public int official_topdown(String s, String[] dictionary) {
        Set<String> dict = new HashSet();

        for (String word : dictionary) {
            dict.add(word);
        }

        return official_topdown(s, dict, 0, new Integer[s.length()]);
    }

    public int official_topdown(String s, Set<String> dict, int index, Integer[] memo) {
        if (index >= s.length()) return 0;

        if (memo[index] != null) {
            return memo[index];
        }

        StringBuilder sb = new StringBuilder();

        int ans = 1 + official_topdown(s, dict, index + 1, memo);

        for (int i = index; i < s.length(); i++) {
            sb.append(s.charAt(i));

            if (dict.contains(sb.toString())) {
                ans = Math.min(ans, official_topdown(s, dict, i + 1, memo));
            }
        }

        return memo[index] = ans;
    }

    public int mySol_bottomup(String s, String[] dictionary) {
        Set<String> dict = new HashSet();

        for (String word : dictionary) {
            dict.add(word);
        }

        int n = s.length();

        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            String word = "";

            for (int j = i; j < n; j++) {
                word += s.charAt(j);

                int length = dict.contains(word) ? 0 : word.length();

                dp[i] = Math.min(dp[i], length + dp[j + 1]);
            }
        }

        return dp[0];
    }

    public int mySol(String s, String[] dictionary) {
        Set<String> dict = new HashSet();

        for (String word : dictionary) {
            dict.add(word);
        }

        return mySol(s, dict, 0, new Integer[s.length()]);
    }

    public int mySol(String s, Set<String> dict, int index, Integer[] memo) {
        if (index >= s.length()) return 0;

        if (memo[index] != null) {
            return memo[index];
        }

        StringBuilder sb = new StringBuilder();

        int ans = Integer.MAX_VALUE;

        for (int i = index; i < s.length(); i++) {
            sb.append(s.charAt(i));

            int length = dict.contains(sb.toString()) ? 0 : sb.length();

            ans = Math.min(ans, length + mySol(s, dict, i + 1, memo));
        }

        return memo[index] = ans;
    }

    class Trie {
        private Trie[] children = new Trie[26];
        private boolean isWord = false;

        public void add(String s) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) t.children[c - 'a'] = new Trie();

                t = t.children[c - 'a'];
            }

            t.isWord = true;
        }

        public boolean find(String s) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) return false;

                t = t.children[c - 'a'];
            }

            return t.isWord;
        }
    }
}