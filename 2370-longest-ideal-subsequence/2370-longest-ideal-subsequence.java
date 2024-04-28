class Solution {
    public int longestIdealString(String s, int k) {
        return tryAgain(s, k);
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