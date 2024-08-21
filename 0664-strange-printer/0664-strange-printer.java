class Solution {
    public int strangePrinter(String s) {
        return othersMoreIntuitive(s);
    }

    public int othersMoreIntuitive(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i < n - 1) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 1 : 2;
            }
        }
        
        for (int len = 2; len < n; len++) {
            for (int start = 0; start + len < n; start++) {
                dp[start][start + len] = len + 1;
                for (int k = 0; k < len; k++) {
                    int temp = dp[start][start + k] + dp[start + k + 1][start + len];
                    dp[start][start + len] = Math.min(
                        dp[start][start + len],
                        s.charAt(start + k) == s.charAt(start + len) ? temp - 1 : temp
                    );
                }
            }
        }
        
        return dp[0][n - 1];
    }

    public int officialDP(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int length = 1; length <= n; length++) {
            for (int left = 0; left <= n - length; left++) {
                int right = left + length - 1;

                dp[left][right] = n;

                int j = -1;

                for (int i = left; i < right; i++) {
                    if (s.charAt(i) != s.charAt(right) && j == -1) {
                        j = i;
                    }

                    if (j != -1) {
                        dp[left][right] = Math.min(dp[left][right], 1 + dp[j][i] + dp[i + 1][right]);
                    }
                }

                if (j == -1) {
                    dp[left][right] = 0;
                }
            }
        }

        return dp[0][n - 1] + 1;
    }

    public int mySolFail(String s) {
        int[] start = new int[26];
        int[] end = new int[26];

        Arrays.fill(start, -1);
        Arrays.fill(end, -1);

        Queue<Character> queue = new LinkedList();

        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            int ch = arr[i] - 'a';

            if (start[ch] == -1) {
                start[ch] = i;
                end[ch] = i;

                queue.add(arr[i]);
            } else {
                end[ch] = i;
            }
        }

        int length = 0;
        int ans = 0;

        int count = arr.length;

        StringBuilder sb = new StringBuilder();

        while (count-- > 0) {
            if (s.equals(sb.toString())) {
                break;
            }

            char ch = queue.poll();

            int si = start[ch - 'a'];
            int ei = end[ch - 'a'];

            if (si < sb.length()) {
                sb.setLength(si);
            }

            sb.append(getSameCharArray(ei - si + 1, ch));

            ans++;

            System.out.println(sb.toString() + ", eq : " + s.equals(sb.toString()));

            queue.add(ch);
        }

        return ans;
    }

    private char[] getSameCharArray(int size, char ch) {
        char[] arr = new char[size];
        Arrays.fill(arr, ch);

        return arr;
    }
}