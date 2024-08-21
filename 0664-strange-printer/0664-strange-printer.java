class Solution {
    public int strangePrinter(String s) {
        return others_bottomup(s);
    }

    public int others_bottomup(String s) {
        // dp[start][end] = min number
        // s.charAt(start) == s.charAt(end)
        // dp[start][end] =

        // s = removeDup(s);

        int n = s.length();

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;

            if (i + 1 < n) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 1 : 2;
            }
        }

        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i + diff < n; i++) {
                int end = i + diff;
                dp[i][end] = diff + 1;

                for (int k = 0; k < diff; k++) {
                    int temp = dp[i][i + k] + dp[i + k + 1][end];

                    dp[i][end] = Math.min(dp[i][end], s.charAt(i + k) == s.charAt(end) ? temp - 1 : temp);
                }
            }
        }

        return dp[0][n - 1];
    }

    private String removeDup(String s) {
        StringBuilder sb = new StringBuilder();

        char prev = '0';

        for (char c : s.toCharArray()) {
            if (prev != c) {
                sb.append(c);
                prev = c;
            }
        }

        return sb.toString();
    }
}