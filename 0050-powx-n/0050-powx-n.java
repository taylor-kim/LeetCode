class Solution {
    public double myPow(double x, int n) {
        double ret = mySol(x, n);

        return n >= 0 ? ret : 1 / ret;
    }

    public double mySol(double x, int n) {
        if (n == 0) return 1d;

        double sqrt = mySol(x, n / 2);
        
        double extra = n % 2 == 0 ? 1l : x;

        double ret = sqrt * sqrt * extra;

        return ret;
    }
}