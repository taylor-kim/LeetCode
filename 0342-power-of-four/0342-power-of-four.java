class Solution {
    public boolean isPowerOfFour(int n) {
        return mySol(n);
    }

    public boolean mySol(int n) {
        while (n > 0 && n % 4 == 0) {
            n /= 4;
        }

        return n == 1;
    }
}