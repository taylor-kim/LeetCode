class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        return mySol_topdown_tle(poured, query_row, query_glass);
    }

    public double mySol_topdown_tle(int poured, int row, int glass) {
        double[][] dp = new double[row + 1][row + 1];
        dp[0][0] = (double)poured;
        topdown(1, dp);

        return Math.min(1, dp[row][glass]);
    }

    public void topdown(int i, double[][] dp) {
        if (i >= dp.length) return;

        for (int j = 0; j < dp[i].length; j++) {
            dp[i][j] += j == 0 ? 0 : Math.max(0, dp[i - 1][j - 1] - 1) / 2;
            dp[i][j] += Math.max(0, dp[i - 1][j] - 1) / 2;
        }

        topdown(i + 1, dp);
    }

    public double mySol_hold(int poured, int row, int glass) {
        List<Double> list = new ArrayList();
        list.add((double)poured);

        for (int i = 0; i <= row; i++) {
            List<Double> next = new ArrayList();
            for (int j = 0; j < list.size(); j++) {
                double remain = next.get(j) - 1;

                if (remain > 0) {
                    
                }
            }
        }

        return 0.0;
    }
}