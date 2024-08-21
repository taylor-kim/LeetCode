class Solution {
    public int strangePrinter(String s) {
        return others_bottomup(s);
    }

    public int official_topdown(String s) {
        // Preprocess the string to remove consecutive duplicate characters
        s = removeDup(s);
        int n = s.length();
        // Initialize memoization array
        Integer[][] memo = new Integer[n][n];
        // Start the recursive process
        return minimumTurns(0, n - 1, s, memo);
    }

    private int minimumTurns(int start, int end, String s, Integer[][] memo) {
        // Base case: empty string requires 0 turns
        if (start > end) {
            return 0;
        }

        // If result is memoized, return it
        if (memo[start][end] != null) {
            return memo[start][end];
        }

        // Initialize with worst case: print each character separately
        int minTurns = 1 + minimumTurns(start + 1, end, s, memo);

        // Try to optimize by finding matching characters
        for (int k = start + 1; k <= end; k++) {
            // if (s.charAt(start) == s.charAt(k)) {
            //     // If match found, try splitting the problem
            //     int turnsWithMatch =
            //         minimumTurns(start, k - 1, s, memo) +
            //         minimumTurns(k + 1, end, s, memo);
            //     minTurns = Math.min(minTurns, turnsWithMatch);
            // }
            int turnsWithMatch = minimumTurns(start, k - 1, s, memo) + minimumTurns(k, end, s, memo);

            if (s.charAt(k - 1) == s.charAt(end)) {
                turnsWithMatch--;
            }

            minTurns = Math.min(minTurns, turnsWithMatch);
        }

        // Memoize and return the result
        return memo[start][end] = minTurns;
    }

    public int others_bottomup(String s) {
        // dp[start][end] = min number
        // s.charAt(start) == s.charAt(end)
        // dp[start][end] =

        s = removeDup(s);

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

                    // dp[i][end] = Math.min(dp[i][end], s.charAt(i) == s.charAt(i + k + 1) ? temp - 1 : temp);
                    // dp[i][end] = Math.min(dp[i][end], s.charAt(i) == s.charAt(end) ? temp - 1 : temp);
                    dp[i][end] = Math.min(dp[i][end], s.charAt(i + k) == s.charAt(end) ? temp - 1 : temp);

                    // bellow does not work.
                    // dp[i][end] = Math.min(dp[i][end], s.charAt(i + k) == s.charAt(i + k + 1) ? temp - 1 : temp);
                    
                }

                // fail
                // for (int k = i + 1; k <= end; k++) {
                //     if (s.charAt(i) == s.charAt(k)) {
                //         int firstPart = dp[i][k - 1];
                //         int secondPart = k + 1 <= end ? dp[k + 1][end] : 0;

                //         dp[i][end] = Math.min(dp[i][end], firstPart + secondPart);
                //     }
                // }
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