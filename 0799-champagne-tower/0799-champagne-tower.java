class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        return spaceOpt(poured, query_row, query_glass);
    }

    public double try_20260214(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row + 1][query_row + 1];

        topdown_20260214(poured, 0, 0, dp);

        return dp[query_row][query_glass];
    }

    private void topdown_20260214(int poured, int i, int j, double[][] dp) {
        if (i >= dp.length || j >= dp.length) return;
        if (poured <= 0) return;

        if (dp[i][j]>= 0) {

        }

        return;
    }

    public double spaceOpt(int poured, int query_row, int query_glass) {
        double[] dp = new double[query_row + 1];
        dp[0] = (double)poured;

        for (int r = 1; r <= query_row; r++) {
            for (int c = r; c > 0; c--) {
                double q = (dp[c - 1] - 1.0) / 2.0;

                if (q > 0) {
                    dp[c - 1] = q;
                    dp[c] += q;
                } else {
                    dp[c - 1] = 0;
                }

                // dp[c + 1] += dp[c] = Math.max(0, q);
            }
        }

        // System.out.println(Arrays.toString(dp));

        /***
        2
        0.5 0.5
        
         */

        return Math.min(1, dp[query_glass]);
    }

    public double practice_other2d(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row + 2][query_row + 2];
        dp[0][0] = (double)poured;

        for (int r = 1; r <= query_row + 1; r++) {
            for (int c = 1; c <= r; c++) {
                double q = (dp[r - 1][c - 1] - 1.0) / 2.0;

                if (q > 0) {
                    dp[r][c - 1] += q;
                    dp[r][c] += q;
                }
            }
        }

        return Math.min(1, dp[query_row][query_glass]);
    }

    public double official(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row + 2][query_row + 2];
        dp[0][0] = (double)poured;

        for (int r = 0; r <= query_row; r++) {
            for (int c = 0; c <= r; c++) {
                double q = (dp[r][c] - 1.0) / 2.0;

                if (q > 0) {
                    dp[r + 1][c] += q;
                    dp[r + 1][c + 1] += q;
                }
            }
        }

        return Math.min(1, dp[query_row][query_glass]);
    }

    public double mySol2_fail(int poured, int r, int c) {
        if (poured == 0) return 0;

        double[][] dp = new double[100][100];
        double[] amount = new double[100];

        // mySol2_fail(poured, 0, 0, r, c, amount, dp);

        return dp[r][c];
    }

    public double mySol2_fail(int poured, int r, double[] amount, double[][] dp) {
        if (poured == 0) return 0;

        if (amount[r] == (double)(r + 1)) {
            return mySol2_fail(poured, r + 1, amount, dp);
        }

        // for (int i = )

        return 0;
    }

    public double mySol_fail(int poured, int r, int c) {
        int cupsOfRow = r + 1;
        int sumOfCups = (r + 1) * (2 + r) / 2;

        if (poured >= sumOfCups) return 1.0;

        int needForFull = sumOfCups - poured;
        int currentFill = cupsOfRow - needForFull;

        if (currentFill <= 0) return 0;

        double evenAmount = 1.0d * currentFill / Math.max(1, cupsOfRow - 1);

        if (c == 0 || c == r) {
            return evenAmount / 2;
        } else {
            return evenAmount;
        }
    }
}