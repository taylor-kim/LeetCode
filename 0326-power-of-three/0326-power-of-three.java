class Solution {
    public boolean isPowerOfThree(int n) {
        return mySol(n);
    }

    public boolean mySol(int n) {
        while (n > 0 && n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}