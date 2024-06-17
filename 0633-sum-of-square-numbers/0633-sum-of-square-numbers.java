class Solution {
    public boolean judgeSquareSum(int c) {
        return official_Fermat_Theorem(c);
    }

    public boolean official_Fermat_Theorem(int c) {
        for (int i = 2; i * i <= c; i++) {
            int count = 0;
            if (c % i == 0) {
                while (c % i == 0) {
                    count++;
                    c /= i;
                }
                if (i % 4 == 3 && count % 2 != 0)
                    return false;
            }
        }
        return c % 4 != 3;
    }

    public boolean official_bs(int c) {
        for (long a = 0; a * a <= c; a++) {
            int bb = c - (int)(a * a);

            if (bs(0, bb, bb)) return true;
        }

        return false;
    }

    private boolean bs(long lo, long hi, int num) {
        if (lo > hi) return false;

        long mid = lo + (hi - lo) / 2;

        if (mid * mid == num) {
            return true;
        } else if (mid * mid < num) {
            return bs(mid + 1, hi, num);
        } else {
            return bs(lo, mid - 1, num);
        }
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