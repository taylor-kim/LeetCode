class Solution {
    public int climbStairs(int n, int[] costs) {
        return editorial(n, costs);
    }

    public int editorial(int n, int[] costs) {
        int a = 0;
        int b = 0;
        int c = 0;

        for (int i = 0; i < n; i++) {
            int next = Math.min(Math.min(a + 9, b + 4), c + 1) + costs[i];

            a = b;
            b = c;
            c = next;
        }

        return c;
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