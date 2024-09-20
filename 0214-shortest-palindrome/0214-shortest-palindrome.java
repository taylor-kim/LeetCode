class Solution {
    public String shortestPalindrome(String s) {
        return mySol2(s);
    }

    public String mySol2(String s) {
        if (isP(s)) return s;

        int[] pi = createPI(s);

        int begin = 0;
        int matching = 0;

        String rev = new StringBuilder().append(s).reverse().toString();

        int max = 0;

        while (begin + matching < rev.length()) {
            if (rev.charAt(begin + matching) == s.charAt(matching)) {
                matching++;
                max = Math.max(max, matching);
            } else {
                if (matching == 0) {
                    begin++;
                } else {
                    begin += matching - pi[matching - 1];
                    matching = pi[matching - 1];
                }
            }
        }

        StringBuilder sb = new StringBuilder().append(s.substring(max)).reverse().append(s);

        if (isP(sb.toString())) {
            return sb.toString();
        } else {
            int left = 0;
            int right = sb.length() - 1;

            while (sb.charAt(left) == sb.charAt(right)) {
                left++;
                right--;
            }

            String ans = sb.toString();

            String prev = ans.substring(0, left);
            String mid = mySol2(ans.substring(left, right + 1));
            String next = ans.substring(right + 1);

            return prev + mid + next;
        }
    }

    private int[] createPI(String s) {
        int[] pi = new int[s.length()];

        int begin = 1;
        int matching = 0;

        char[] arr = s.toCharArray();

        while (begin + matching < s.length()) {
            if (arr[begin + matching] == arr[matching]) {
                matching++;
                pi[begin + matching - 1] = matching;
            } else {
                if (matching == 0) {
                    begin++;
                } else {
                    begin += matching - pi[matching - 1];
                    matching = pi[matching - 1];
                }
            }
        }

        return pi;
    }

    public String mySol_tle(String s) {
        int n = s.length();

        if (n == 0) return "";

        int[] dp = new int[2];

        for (int i = 0; i < n; i++) {
            fillDp(s, i, i, dp);
            fillDp(s, i, i + 1, dp);
        }

        StringBuilder sb = new StringBuilder();

        sb.append(s.substring(dp[1] + 1)).reverse().append(s);

        return sb.toString();
    }

    private void fillDp(String s, int left, int right, int[] dp) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) break;

            if (left == 0 && right - left + 1 > dp[0]) {
                dp[0] = right - left + 1;
                dp[1] = right;
            }

            left--;
            right++;
        }
    }

    private boolean isP(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }

        return true;
    }
}