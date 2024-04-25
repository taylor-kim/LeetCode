class Solution {
    public int longestIdealString(String s, int k) {
        return mySol_lis_hint_by_candle(s, k);
    }

    public int mySol_lis_hint_by_candle(String s, int k) {
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;
        Map<Character, Integer> indices = new HashMap();

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            char c = s.charAt(i);

            for (int j = 0; j < 26; j++) {

                char t = (char)(j + 'a');

                if (Math.abs(c - t) <= k && indices.containsKey(t)) {
                    dp[i] = Math.max(dp[i], dp[indices.get(t)] + 1);
                }
            }

            ans = Math.max(ans, dp[i]);

            indices.put(c, i);
        }

        return ans;
    }

    public int mySol_lis_bs(String s, int k) {
        int n = s.length();
        char[] arr = new char[n];

        int length = 0;
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // int index = Arrays.binarySearch(arr, 0, length, c, (o1, o2) -> {
            //     return -1;
            // });
        }

        return -1;
    }

    public int mySol_lis_tle(String s, int k) {
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                char c1 = s.charAt(i);
                char c2 = s.charAt(j);

                int diff = Math.abs(c1 - c2);

                if (diff <= k) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}