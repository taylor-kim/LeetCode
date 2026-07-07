class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        return official(s, queries);
    }

    public int[] official(String s, int[][] queries) {
        int maxN = 100001;
        int n = s.length();
        long[] xSum = new long[n + 1];
        int[] pSum = new int[n + 1];
        int[] count = new int[n + 1];
        long[] pow10 = new long[maxN];

        int mod = (int)1e9 + 7;

        pow10[0] = 1;

        for (int i = 1; i < maxN; i++) {
            pow10[i] = (pow10[i - 1] * 10) % mod;
        }

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int digit = c - '0';

            if (digit > 0) {
                xSum[i + 1] = (xSum[i] * 10 + digit) % mod;
                count[i + 1] = count[i] + 1;
            } else {
                xSum[i + 1] = xSum[i];
                count[i + 1] = count[i];
            }

            pSum[i + 1] = (pSum[i] + digit) % mod;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            int sum = (pSum[query[1] + 1] - pSum[query[0]] + mod) % mod;
            int length = count[query[1] + 1] - count[query[0]];
            long x = (xSum[query[1] + 1] - ((xSum[query[0]] * pow10[length]) % mod) + mod) % mod;

            ans[i] = (int)((x * sum) % mod);
        }

        return ans;
    }

    public int[] mySol2_fail(String s, int[][] queries) {
        int n = s.length();
        int[] xSum = new int[n + 1];
        int[] pSum = new int[n + 1];
        int mod = (int)1e9 + 7;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int digit = c - '0';

            if (digit > 0) {
                xSum[i + 1] = (xSum[i] * 10 + digit) % mod;
            } else {
                xSum[i + 1] = xSum[i];
            }

            pSum[i + 1] = (pSum[i] + digit) % mod;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            int sum = (pSum[query[1] + 1] - pSum[query[0]] + mod) % mod;
            int x = (xSum[query[1] + 1] - xSum[query[0]] + mod) % mod;

            ans[i] = (int)(((long)x * sum) % mod);
        }

        return ans;
    }

    public int[] mySol_tle(String s, int[][] queries) {
        int n = s.length();

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            ans[i] = getAns(s, query[0], query[1]);
        }

        return ans;
    }

    private int getAns(String s, int from, int to) {
        long mod = (int)1e9 + 7;
        long x = 0;
        long sum = 0;

        for (int i = from; i <= to; i++) {
            char c = s.charAt(i);
            int d = c - '0';
            
            if (d > 0) {
                x = ((x * 10) + d) % mod;
            }

            sum = (sum + d) % mod;
        }

        return (int)((x * sum) % mod);
    }
}