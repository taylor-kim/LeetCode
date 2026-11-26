class Solution {
    public boolean canAliceWin(int n) {
        return mySol(n);
    }

    public boolean mySol(int n) {
        return mySol(n, 10);
    }

    public boolean mySol(int n, int sub) {
        if (n - sub < 0) return false;

        return !mySol(n - sub, sub - 1);
    }
}