class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        return try_20260711(s, queries);
    }

    /**
    gemini : mod inverse 로 푸는 방법이 없다고 함
     */
    public int[] practice_inverse_of_mod(String s, int[][] queries) {
        return null;
    }

    private long modInverse(long num, int mod) {
        return qpow(num, mod - 2, mod);
    }

    private long qpow(long base, long power, int mod) {
        long res = 1;
        base = base % mod;

        while (power > 0) {
            if ((power & 1) == 1) {
                res = (res * base) % mod;
            }
            power >>= 1;
            base = (base * base) % mod;
        }

        return res;
    }

    public int[] try_20260711(String s, int[][] queries) {
        int n = s.length();
        int[] pSum = new int[n + 1];
        long[] xSum = new long[n + 1];
        int[] lengths = new int[n + 1];
        int mod = (int)1e9 + 7;
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % mod;
        }

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            pSum[i + 1] = (pSum[i] + digit) % mod;

            if (digit > 0) {
                xSum[i + 1] = (xSum[i] * 10 + digit) % mod;
                lengths[i + 1] = lengths[i] + 1;
            } else {
                xSum[i + 1] = xSum[i];
                lengths[i + 1] = lengths[i];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int sum = (pSum[r + 1] - pSum[l] + mod) % mod;
            long x = (xSum[r + 1] - ((xSum[l] * pow10[lengths[r + 1] - lengths[l]]) % mod) + mod) % mod;

            ans[i] = (int)((sum * x) % mod);
        }

        return ans;
    }

    public int[] official(String s, int[][] queries) {
        int n = s.length();
        long[] xSum = new long[n + 1];
        int[] pSum = new int[n + 1];
        int[] count = new int[n + 1];
        long[] pow10 = new long[n + 1];

        int mod = (int)1e9 + 7;

        pow10[0] = 1;

        for (int i = 1; i <= n; i++) {
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