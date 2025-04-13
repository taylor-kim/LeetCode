class Solution {
    public int countGoodNumbers(long n) {
        return mySol(n);
    }

    public int mySol(long n) {
        int mod = (int)1e9 + 7;

        long evenCount = n / 2 + (n % 2);
        long oddCount = n / 2;

        long evenTotal = pow(5l, evenCount, mod);
        long oddTotal = pow(4l, oddCount, mod);

        return (int)((evenTotal * oddTotal) % mod);
    }

    private long pow(long base, long exp, int mod) {
        if (exp == 0) return 1l;

        long sqrt = pow(base, exp / 2, mod) % mod;

        long ret = (sqrt * sqrt) % mod;

        if (exp % 2 == 1) {
            ret = (ret * base) % mod;
        }

        return ret;
    }
}