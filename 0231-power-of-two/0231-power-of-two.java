class Solution {
    public boolean isPowerOfTwo(int n) {
        return others_good(n);
    }

    public boolean others_good(int n) {
        return n > 0 && (n & (n - 1)) == 0;
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