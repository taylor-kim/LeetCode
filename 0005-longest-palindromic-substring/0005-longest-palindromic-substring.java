class Solution {
    public String longestPalindrome(String s) {
        return mySol(s);
    }

    public String mySol(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }

        int[] indices = {0, 0};
        char[] arr = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int[] odd = check(arr, i, i, dp);
            int[] even = check(arr, i, i + 1, dp);

            if (odd[0] >= 0 && odd[1] - odd[0] > indices[1] - indices[0]) {
                indices = odd;
            }

            if (even[0] >= 0 && even[1] - even[0] > indices[1] - indices[0]) {
                indices = even;
            }
        }

        return s.substring(indices[0], indices[1] + 1);
    }

    private int[] check(char[] arr, int left, int right, boolean[][] dp) {
        int[] indices = {-1, -1};

        while (left >= 0 && right < arr.length) {
            if (left == right || (arr[left] == arr[right] && (right - left <= 2 || dp[left + 1][right - 1]))) {
                dp[left][right] = true;

                indices[0] = left;
                indices[1] = right;
            } else {
                break;
            }
            left--;
            right++;
        }

        return indices;
    }
}