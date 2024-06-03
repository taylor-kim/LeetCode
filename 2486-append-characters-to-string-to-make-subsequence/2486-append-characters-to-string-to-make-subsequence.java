class Solution {
    public int appendCharacters(String s, String t) {
        return official_greedy(s, t);
    }

    public int official_greedy(String s, String t) {
        int index = 0;
        int pos = 0;

        while (pos < t.length() && index < s.length()) {
            if (s.charAt(index) == t.charAt(pos)) {
                pos++;
            }
            index++;
        }

        return t.length() - pos;
    }

    public int mySol_bottomup(String s, String t) {
        int[] dp = new int[t.length() + 1];

        for (int i = 0; i < t.length(); i++) {
            
        }

        return dp[t.length()];
    }

    public int mySol_topdown(String s, String t) {
        return mySol_topdown(s, t, 0, 0, new Integer[s.length()][t.length()]);
    }

    public int mySol_topdown(String s, String t, int i, int j, Integer[][] memo) {
        if (j >= t.length()) return 0;
        if (i >= s.length()) return t.length() - j;

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int include = Integer.MAX_VALUE;

        if (s.charAt(i) == t.charAt(j)) {
            include = mySol_topdown(s, t, i + 1, j + 1, memo);
        }

        int exclude = mySol_topdown(s, t, i + 1, j, memo);

        return memo[i][j] = Math.min(include, exclude);
    }

    public int mySol(String s, String t) {
        List<Integer>[] freq = new List[26];

        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList();
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (freq[c - 'a'] == null) freq[c - 'a'] = new ArrayList();

            freq[c - 'a'].add(i);
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);

            if (freq[c - 'a'] == null || freq[c - 'a'].size() == 0) {
                return t.length() - i;
            } else if (freq[c - 'a'].get(0) < 0) {
                
            }
        }

        return 0;
    }
}