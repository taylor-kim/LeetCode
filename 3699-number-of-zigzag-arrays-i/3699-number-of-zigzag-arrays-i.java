class Solution {
    public int zigZagArrays(int n, int l, int r) {
        return after_hint_and_editorial(n, l, r);
    }

    public int after_hint_and_editorial(int n, int l, int r) {
        int[] dpInc = new int[r + 1];
        int[] dpDesc = new int[r + 1];
        int[] pSumInc = new int[r + 3];
        int[] pSumDesc = new int[r + 3];

        for (int i = l; i <= r; i++) {
            dpInc[i] = 1;
            dpDesc[i] = 1;
            pSumInc[i + 1] = i - l + 1;
            pSumDesc[i + 1] = i - l + 1;
        }

        int mod = (int)1e9 + 7;

        for (int i = 1; i <= n; i++) {
            for (int num = l; num <= r; num++) {
                //current is inc
                dpInc[num] = pSumDesc[num];

                //current is desc
                dpDesc[num] = (pSumInc[r + 1] - pSumInc[num + 1] + mod) % mod;
            }

            for (int num = l; num <= r; num++) {
                pSumDesc[num + 1] = (pSumDesc[num] + dpDesc[num]) % mod;

                pSumInc[num + 1] = (pSumInc[num] + dpInc[num]) % mod;
            }
        }

        return (dpInc[r] + dpDesc[l]) % mod;
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