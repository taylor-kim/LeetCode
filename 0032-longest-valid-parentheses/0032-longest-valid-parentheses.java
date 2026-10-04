class Solution {
    public int longestValidParentheses(String s) {
        return test(s);
    }

    public int test(String s) {
        int n = s.length();
        int ans = 0;
        int open = 0;
        int left = 0;
        int right = 0;

        for (right = 0; right < n; right++) {
            open += s.charAt(right) == '(' ? 1 : -1;

            // while (open < 0) {
            //     open += s.charAt(left++) == '(' ? -1 : 1;
            // }
            if (open < 0) {
                left = right + 1;
                open = 0;
                continue;
            }

            if (open == 0) {
                ans = Math.max(ans, right - left + 1);
            }
        }

        open = 0;

        for (left = n - 1, right = n - 1; left >= 0; left--) {
            open += s.charAt(left) == '(' ? -1 : 1;

            while (open < 0) {
                open += s.charAt(right--) == '(' ? 1 : -1;
            }

            if (open == 0) {
                ans = Math.max(ans, right - left + 1);
            }
        }

        return ans;
    }

    public int official_stack(String s) {
        return -1;
    }

    public int official_dp(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;

        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);

            if (c == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        // System.out.println(Arrays.toString(dp));

        return ans;
    }

    public int mySol2(String s) {
        int n = s.length();
        int ans = 0;
        int open = 0;
        int left = 0;
        int right = 0;

        for (right = 0; right < n; right++) {
            open += s.charAt(right) == '(' ? 1 : -1;

            while (open < 0) {
                open += s.charAt(left++) == '(' ? -1 : 1;
            }

            if (open == 0) {
                ans = Math.max(ans, right - left + 1);
            }
        }

        open = 0;

        for (left = n - 1, right = n - 1; left >= 0; left--) {
            open += s.charAt(left) == '(' ? -1 : 1;

            while (open < 0) {
                open += s.charAt(right--) == '(' ? 1 : -1;
            }

            if (open == 0) {
                ans = Math.max(ans, right - left + 1);
            }
        }

        return ans;
    }

    public int mySol_tle(String s) {
        int n = s.length();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int open = 0;
            for (int j = i; j < n; j++) {
                open += s.charAt(j) == '(' ? 1 : - 1;

                if (open < 0) {
                    i = j;
                    break;
                }

                if (open == 0) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }
}