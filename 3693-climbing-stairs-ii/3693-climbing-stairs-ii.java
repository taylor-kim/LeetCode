class Solution {
    public int climbStairs(int n, int[] costs) {
        return mySol(n, costs);
    }

    public int mySol(int n, int[] costs) {
        return topdown(n, costs, 0, new Integer[n]);
    }

    private int topdown(int n, int[] costs, int step, Integer[] memo) {
        if (step == n) return 0;

        if (memo[step] != null) {
            return memo[step];
        }

        int ans = Integer.MAX_VALUE;

        for (int delta = 1; delta <= 3; delta++) {
            int nextStep = step + delta;

            if (nextStep <= n) {
                int cost = costs[nextStep - 1] + (int)Math.pow(delta, 2);
                ans = Math.min(ans, topdown(n, costs, nextStep, memo) + cost);
            }
        }

        return memo[step] = ans;
    }
}