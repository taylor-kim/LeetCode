class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        return mySol_iter_space_opt(poured, query_row, query_glass);
    }

    public double mySol_iter_space_opt(int poured, int row, int glass) {
        double[] dp = new double[row + 1];
        dp[0] = (double)poured;

        int i = 1;

        while (i <= row) {
            for (int j = i; j >= 0; j--) {
                dp[j] = Math.max(0, dp[j] - 1) / 2;
                dp[j] += j == 0 ? 0 : Math.max(0, dp[j - 1] - 1) / 2;
            }
            i++;
        }

        return Math.min(1, dp[glass]);
    }

    public double mySol_iter(int poured, int row, int glass) {
        double[][] dp = new double[row + 1][row + 1];
        dp[0][0] = (double)poured;

        int i = 1;

        while (i <= row) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] += j == 0 ? 0 : Math.max(0, dp[i - 1][j - 1] - 1) / 2;
                dp[i][j] += Math.max(0, dp[i - 1][j] - 1) / 2;
            }
            i++;
        }

        return Math.min(1, dp[row][glass]);
    }

    public double mySol_topdown(int poured, int row, int glass) {
        double[][] dp = new double[row + 1][row + 1];
        dp[0][0] = (double)poured;
        topdown(1, dp);

        return Math.min(1, dp[row][glass]);
    }

    public void topdown(int i, double[][] dp) {
        if (i >= dp.length) return;

        double fall = 0;

        for (int j = 0; j < dp[i].length; j++) {
            dp[i][j] += j == 0 ? 0 : Math.max(0, dp[i - 1][j - 1] - 1) / 2;
            dp[i][j] += Math.max(0, dp[i - 1][j] - 1) / 2;

            fall += dp[i][j];
        }

        if (fall > 0) {
            topdown(i + 1, dp);
        }
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