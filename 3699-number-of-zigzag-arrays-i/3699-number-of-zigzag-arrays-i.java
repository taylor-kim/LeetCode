class Solution {
    public int zigZagArrays(int n, int l, int r) {
        return after_hint_and_editorial_further(n, l, r);
    }

    public int after_hint_and_editorial_further(int n, int l, int r) {
        int[] dp = new int[r + 1];
        int[] pSum = new int[r + 3];

        for (int i = l; i <= r; i++) {
            // dp[i] = 1;
            pSum[i + 1] = i - l + 1;
        }

        int mod = (int)1e9 + 7;

        for (int i = 1; i < n; i++) {
            for (int num = l; num <= r; num++) {
                if (i % 2 == 0) {
                    dp[num] = pSum[num];
                } else {
                    dp[num] = (pSum[r + 1] - pSum[num + 1] + mod) % mod;
                }
            }

            for (int num = l; num <= r; num++) {
                pSum[num + 1] = (pSum[num] + dp[num]) % mod;
            }
        }

        return (pSum[r + 1] + pSum[r + 1]) % mod;
    }

    public int after_hint_and_editorial(int n, int l, int r) {
        int[] dpBigger = new int[r + 1];
        int[] dpSmaller = new int[r + 1];
        int[] pSumBigger = new int[r + 3];
        int[] pSumSmaller = new int[r + 3];

        for (int i = l; i <= r; i++) {
            // dpBigger[i] = 1;
            // dpSmaller[i] = 1;
            pSumBigger[i + 1] = i - l + 1;
            pSumSmaller[i + 1] = i - l + 1;
        }

        int mod = (int)1e9 + 7;

        for (int i = 1; i < n; i++) {
            for (int num = l; num <= r; num++) {
                //current is inc
                dpBigger[num] = pSumSmaller[num];

                //current is desc
                dpSmaller[num] = (pSumBigger[r + 1] - pSumBigger[num + 1] + mod) % mod;
            }

            for (int num = l; num <= r; num++) {
                pSumSmaller[num + 1] = (pSumSmaller[num] + dpSmaller[num]) % mod;

                pSumBigger[num + 1] = (pSumBigger[num] + dpBigger[num]) % mod;
            }
        }

        // return (dpInc[r] + dpDesc[l]) % mod;
        return (pSumBigger[r + 1] + pSumBigger[r + 1]) % mod;
    }

    public int mySol(int n, int l, int r) {
        int ans = 0;

        StringBuilder sb = new StringBuilder();

        int mod = (int)1e9 + 7;

        Integer[][] memo = new Integer[r - l + 1][3];

        for (int i = l; i <= r; i++) {
            ans = (ans + topdown(n, l, r, 1, i, 1, memo)) % mod;
            ans = (ans + topdown(n, l, r, 1, i, -1, memo)) % mod;
        }

        return ans;
    }

    public int topdown(int n, int l, int r, int pos, int num, int delta, Integer[][] memo) {
        // if (num < l || num > r) return 0;

        if (pos == n) {
            return 1;
        }

        if (memo[num - l][delta + 1] != null) {
            return memo[num - l][delta + 1];
        }

        int ans = 0;
        int mod = (int)1e9 + 7;

        if (delta > 0) {
            for (int i = num + 1; i <= r; i++) {
                ans = (ans + topdown(n, l, r, pos + 1, i, delta * -1, memo)) % mod;
            }
        } else {
            for (int i = num - 1; i >= l; i--) {
                ans = (ans + topdown(n, l, r, pos + 1, i, delta * -1, memo)) % mod;
            }
        }

        return memo[num - l][delta + 1] = ans;
    }
}