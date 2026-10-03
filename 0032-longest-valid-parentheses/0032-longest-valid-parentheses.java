class Solution {
    public int longestValidParentheses(String s) {
        return mySol2(s);
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