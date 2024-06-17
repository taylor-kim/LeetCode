class Solution {
    public boolean judgeSquareSum(int c) {
        return mySol_fail(c);
    }

    public boolean official_bs(int c) {
        for (int a = 0; a * a <= c; a++) {
            int bb = c - (a * a);

            // if (bs(0, bb, bb)) return true;
        }

        return false;
    }

    public boolean official_sqrt(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b)
                return true;
        }
        return false;
    }

    public boolean mySol_fail(int c) {
        for (long a = 0; a * a <= c; a++) {
            int aa = (int)(a * a);
            int bb = c - aa;

            double sqrt = Math.sqrt(bb);
            int nSqrt = (int)sqrt;

            if (sqrt - nSqrt == 0) return true;
        }

        return false;
    }
}