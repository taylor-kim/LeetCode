class Solution {
    public int countSubstrings(String s) {
        return study_dp(s);
    }

    public int study_dp(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        boolean[][] dp = new boolean[n][n];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            ans++;

            if (i < n - 1) {
                if (arr[i] == arr[i + 1]) {
                    dp[i][i + 1] = true;
                    ans++;
                } else {
                    dp[i][i + 1] = false;
                }
            }
        }

        for (int diff = 2; diff < n; diff++) {
            for (int i = 0, j = i + diff; j < n; i++, j++) {
                if (dp[i + 1][j - 1] && arr[i] == arr[j]) {
                    dp[i][j] = true;
                    ans++;
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return ans;
    }

    public int mySol(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            ans += count(arr, i, i);
            ans += count(arr, i, i + 1);
        }

        return ans;
    }

    private int count(char[] arr, int left, int right) {
        int ans = 0;

        while (left >= 0 && right < arr.length) {
            if (arr[left--] == arr[right++]) {
                ans++;
            } else {
                break;
            }
        }

        return ans;
    }
}