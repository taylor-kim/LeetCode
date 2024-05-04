class Solution {
    public int longestIdealString(String s, int k) {
        return official_topdown(s, k);
    }

    public int official_topdown(String s, int k) {
        int n = s.length();
        int ans = 0;

        Integer[][] dp = new Integer[n][26];

        for (int c = 0; c < 26; c++) {
            ans = Math.max(ans, official_topdown(n - 1, c, s, k, dp));
        }

        return ans;
    }

    public int official_topdown(int index, int c, String s, int k, Integer[][] dp) {
        if (dp[index][c] != null) {
            return dp[index][c];
        }

        dp[index][c] = 0;

        boolean match = c == (s.charAt(index) - 'a');

        if (match) {
            dp[index][c] = 1;
        }

        if (index > 0) {
            dp[index][c] = official_topdown(index - 1, c, s, k, dp);

            if (match) {
                for (int next = 0; next < 26; next++) {
                    if (Math.abs(c - next) <= k) {
                        dp[index][c] = Math.max(dp[index][c], official_topdown(index - 1, next, s, k, dp) + 1);
                    }
                }
            }
        }

        return dp[index][c];
    }

    public int try_topdown_fail(String s, int k) {
        return try_topdown(s, k, -1, 0, new Integer[s.length()][s.length()]);
    }

    public int try_topdown(String s, int k, int prev, int index, Integer[][] memo) {
        if (index >= s.length()) return 0;

        if (prev != -1 && memo[prev][index] != null) {
            // return memo[prev][index];
        }

        char c = s.charAt(index);

        int include = 1;

        if (prev == -1 || Math.abs(s.charAt(prev) - c) <= k) {
            include = 1 + try_topdown(s, k, index, index + 1, memo);
        }
        int exclude = try_topdown(s, k, prev, index + 1, memo);

        // return memo[prev][index] = Math.max(include, exclude);
        return Math.max(include, exclude);
    }

    public int tryAgain(String s, int k) {
        int n = s.length();
        int[] dp = new int[26];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);

            for (int j = 0; j < 26; j++) {
                char c2 = (char)(j + 'a');

                if (Math.abs(c2 - c1) <= k) {
                    dp[c1 - 'a'] = Math.max(dp[c1 - 'a'], dp[c2 - 'a']);
                }
            }

            dp[c1 - 'a']++;

            ans = Math.max(ans, dp[c1 - 'a']);
        }

        return ans;
    }
}