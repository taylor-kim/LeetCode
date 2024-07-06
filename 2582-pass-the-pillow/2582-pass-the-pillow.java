class Solution {
    public int passThePillow(int n, int time) {
        return mySol(n, time);
    }

    public int mySol(int n, int time) {
        int round = time / (n - 1);
        int odd = time % (n - 1);

        if (round % 2 == 1) {
            return n - odd;
        } else {
            return 1 + odd;
        }
    }
}