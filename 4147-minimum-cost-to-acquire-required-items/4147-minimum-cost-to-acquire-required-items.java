class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        return mySol(cost1, cost2, costBoth, need1, need2);
    }

    public long mySol(int cost1, int cost2, int costBoth, int need1, int need2) {
        long ans = 0;

        if (cost1 + cost2 >= costBoth) {
            int min = Math.min(need1, need2);

            need1 -= min;
            need2 -= min;

            ans += 1l * costBoth * min;
        }

        if (need1 > 0) {
            ans += 1l * Math.min(cost1, costBoth) * need1;
        }

        if (need2 > 0) {
            ans += 1l * Math.min(cost2, costBoth) * need2;
        }

        return ans;
    }
}