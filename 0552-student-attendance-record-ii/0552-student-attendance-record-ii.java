class Solution {
    public int checkRecord(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        return mySol(n, 0, 0, 0, new Integer[n][2][3]);
    }

    public int mySol(int n, int index, int a, int l, Integer[][][] memo) {
        if (a >= 2) return 0;

        if (l >= 3) return 0;

        if (index >= n) {
            return 1;
        }

        if (memo[index][a][l] != null) {
            return memo[index][a][l];
        }

        int mod = (int)1e9 + 7;

        long ans = mySol(n, index + 1, a + 1, 0, memo) % mod;

        ans = (mySol(n, index + 1, a, l + 1, memo) + ans) % mod;

        ans = (mySol(n, index + 1, a, 0, memo) + ans) % mod;

        return memo[index][a][l] = (int)ans;
    }
}