class Solution {
    public boolean isPowerOfTwo(int n) {
        return mySol(n);
    }

    public boolean mySol(int n) {
        int count = 0;

        if (n <= 0) {
            return false;
        }

        for (int i = 0; i < 31; i++) {
            if ((n & (1 << i)) != 0) count++;

            if (count > 1) return false;
        }

        return true;
    }
}