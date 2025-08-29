class Solution {
    public long flowerGame(int n, int m) {
        return editorial(n, m);
    }

    public long editorial(int n, int m) {
        return ((long) m * n) / 2;
    }

    public long mySol_fail(int n, int m) {
        return topdown(n, m, 0);
    }

    public long topdown(int n, int m, int player) {
        if (n <= 0 && m <= 0) return player;

        long ans = 0;

        int nextPlayer = (player + 1) % 2;

        if (n > 0) {
            ans += topdown(n - 1, m, nextPlayer);
        }
        if (m > 0) {
            ans += topdown(n, m - 1, nextPlayer);
        }

        return ans;
    }
}