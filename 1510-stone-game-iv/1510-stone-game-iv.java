class Solution {
    public boolean winnerSquareGame(int n) {
        return mySol(n);
    }

    public boolean mySol(int n) {
        return topdown(n, new Boolean[n + 1]);
    }

    private boolean topdown(int n, Boolean[] memo) {
        if (n <= 0) return false;

        if (memo[n] != null) {
            return memo[n];
        }

        for (int i = 1; i * i <= n; i++) {
            if (!topdown(n - i * i, memo)) {
                return memo[n] = true;
            }
        }

        return memo[n] = false;
    }
}