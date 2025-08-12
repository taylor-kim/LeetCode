class Solution {
    public int numberOfWays(int n, int x) {
        return mySol(n, x);
    }

    public int mySol(int n, int x) {
        return topdown(n, 1, x, new Integer[n + 1][n + 1]);
    }

    public int topdown(int n, int num, int x, Integer[][] memo) {
        if (n <= 0) return n == 0 ? 1 : 0;
        if (num > n) return 0;

        int mod = (int)1e9 + 7;

        if (memo[n][num] != null) return memo[n][num];

        int include = topdown(n - (int)Math.pow(num, x), num + 1, x, memo) % mod;
        int exclude = topdown(n, num + 1, x, memo) % mod;

        return memo[n][num] = (include + exclude) % mod;
    }
}