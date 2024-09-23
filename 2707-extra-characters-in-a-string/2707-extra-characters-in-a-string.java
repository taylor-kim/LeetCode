class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        return mySol_bottomup(s, dictionary);
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
}