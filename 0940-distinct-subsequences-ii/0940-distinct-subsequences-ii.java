class Solution {
    public int distinctSubseqII(String s) {
        return gemini(s);
    }

    public int gemini(String s) {
        int n = s.length();
        int mod = (int)1e9 + 7;
        int ans = 0;
        int[] count = new int[26];
        Arrays.fill(count, 1);

        for (char c : s.toCharArray()) {
            int node = c - 'a';

            int newNodeCount = count[node];

            ans = (ans + newNodeCount) % mod;

            for (int i = 0; i < 26; i++) {
                if (i == node) continue;
                count[i] = (count[i] + newNodeCount) % mod;
            }

            // System.out.println(Arrays.toString(count));
        }

        return ans;
    }

    public int mySol_fail(String s) {
        // include : char c => t.add(c), ans += 1 or 0, topdown(i + 1, t.children[c - 'a'])
        // exclude : topdown(i + 1, t)

        int[] ans = {0};

        Map<Integer, Trie> map = new HashMap();
        map.put(0, new Trie(0));

        topdown(s, 0, 0, ans, map, new boolean[s.length()][Integer.MAX_VALUE]);

        return ans[0];
    }

    private void topdown(String s, int i, int ti, int[] ans, Map<Integer, Trie> map, boolean[][] visit) {
        if (i >= s.length()) return;

        if (visit[i][ti]) return;

        visit[i][ti] = true;

        char c = s.charAt(i);

        Trie t = map.get(ti);

        if (t.children[c - 'a'] == null) {
            int nextTi = map.size();

            t.children[c - 'a'] = new Trie(nextTi);

            map.put(t.children[c - 'a'].index, t.children[c - 'a']);

            ans[0] = (ans[0] + 1) % ((int)1e9 + 7);

            topdown(s, i + 1, nextTi, ans, map, visit);
        }

        topdown(s, i + 1, ti, ans, map, visit);
    }

    class Trie {
        Trie[] children = new Trie[26];
        int index;

        public Trie(int index) {
            this.index = index;
        }
    }
}