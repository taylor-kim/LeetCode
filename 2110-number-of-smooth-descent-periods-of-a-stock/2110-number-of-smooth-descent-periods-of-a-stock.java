class Solution {
    public long getDescentPeriods(int[] prices) {
        return mySol(prices);
    }

    public long mySol(int[] prices) {
        long ans = 0;

        boolean isFirst = true;
        long periods = 0;

        for (int i = 0; i < prices.length; i++) {
            if (periods != 0 && prices[i - 1] - 1 != prices[i]) {
                periods = 0;
            }

            periods++;
            ans += periods;
        }

        return ans;
    }
}