class Solution {
    public long maximumProfit(int[] prices, int k) {
        return after_sol(prices, k);
    }

    public long after_sol(int[] prices, int k) {
        return topdown3(prices, prices.length - 1, k, 0, new Long[prices.length][k + 1][3]);
    }

    public long topdown3(int[] prices, int index, int k, int state, Long[][][] memo) {
        if (k == 0) return 0;

        if (index == 0) {
            return state == 0 ? 0 : state == 1 ? -prices[index] : prices[index];
        }

        if (memo[index][k][state] != null) return memo[index][k][state];

        long ans = 0;

        int p = prices[index];

        if (state == 0) {
            ans = Math.max(
                topdown3(prices, index - 1, k, state, memo),
                Math.max(
                    topdown3(prices, index - 1, k, 1, memo) + p,
                    topdown3(prices, index - 1, k, 2, memo) - p
                )
            );
        } else if (state == 1) {
            ans = Math.max(
                topdown3(prices, index - 1, k, state, memo),
                topdown3(prices, index - 1, k - 1, 0, memo) - p
            );
        } else {
            ans = Math.max(
                topdown3(prices, index - 1, k, state, memo),
                topdown3(prices, index - 1, k - 1, 0, memo) + p
            );
        }

        return memo[index][k][state] = ans;
    }

    public long hint_fail(int[] prices, int k) {
        return topdown2(prices, k, 0, 0, 0);
    }

    public long topdown2(int[] prices, int k, int index, int isBuy, int isRunning) {
        if (index >= prices.length || k == 0) {
            return -isRunning;
        }

        long ans = topdown2(prices, k, index + 1, isBuy, isRunning);

        long sub = topdown2(prices, k - isRunning, index + 1, (isBuy + 1) % 2, (isRunning + 1) % 2);

        if (sub >= 0) {
            ans = Math.max(ans, sub + (prices[index] * (isBuy == 0 ? -1 : 1)));
        }

        return ans;
    }

    public long mySol_fail(int[] prices, int k) {
        return topdown(prices, k, 0, new Long[prices.length][k + 1]);
    }

    public long topdown(int[] prices, int k, int index, Long[][] memo) {
        if (index >= prices.length || k == 0) return 0;

        if (memo[index][k] != null) return memo[index][k];

        long ans = 0;

        for (int i = index + 1; i < prices.length; i++) {
            long buyAndSell = prices[i] - prices[index] + topdown(prices, k - 1, i + 1, memo);
            long sellAndBuy = prices[index] - prices[i] + topdown(prices, k - 1, i + 1, memo);

            ans = Math.max(ans, Math.max(buyAndSell, sellAndBuy));
        }

        for (int i = index + 1; i < prices.length; i++) {
            long sub = prices[index] - prices[i] + topdown(prices, k - 1, i + 1, memo);

            ans = Math.max(ans, sub);
        }

        ans = Math.max(ans, topdown(prices, k, index + 1, memo));

        return memo[index][k] = ans;
    }
}