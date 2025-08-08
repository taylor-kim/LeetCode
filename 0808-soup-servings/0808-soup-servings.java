class Solution {
    public double soupServings(int n) {
        return official_topdown(n);
    }

    public double official_topdown(int n) {
        if (n == 0) return 0.5;

        int m = (int)Math.ceil(n / 25.0);

        Map<Integer, Map<Integer, Double>> memo = new HashMap();

        for (int k = 1; k <= m; k++) {
            if (official_topdown(k, k, memo) > 1 - (1e-5)) {
                return 1.0;
            }
        }

        // return official_topdown(m, m, memo);
        return memo.get(m).get(m);
    }

    public double official_topdown(int a, int b, Map<Integer, Map<Integer, Double>> memo) {
        if (a <= 0 && b > 0) return 1;
        if (a <= 0 && b <= 0) return 0.5;
        if (a > 0 && b <= 0) return 0;

        if (memo.containsKey(a) && memo.get(a).containsKey(b)) return memo.get(a).get(b);

        double r1 = 0.25 * official_topdown(a - 4, b - 0, memo);
        double r2 = 0.25 * official_topdown(a - 3, b - 1, memo);
        double r3 = 0.25 * official_topdown(a - 2, b - 2, memo);
        double r4 = 0.25 * official_topdown(a - 1, b - 3, memo);

        memo.computeIfAbsent(a, k -> new HashMap()).put(b, r1 + r2 + r3 + r4);

        return memo.get(a).get(b);
    }

    public double trick(int n) {
        if (n >= 4800) return 1;

        return mySol2(n);
    }

    public double mySol2(int n) {
        return topdown2(n, n, new Double[n + 1][n + 1]);
    }

    public double topdown2(int a, int b, Double[][] memo) {
        if (a <= 0 && b > 0) return 1;
        if (a <= 0 && b <= 0) return 0.5;
        if (a > 0 && b <= 0) return 0;

        if (memo[a][b] != null) return memo[a][b];

        double r1 = topdown2(a - 100, b - 0, memo);
        double r2 = topdown2(a - 75, b - 25, memo);
        double r3 = topdown2(a - 50, b - 50, memo);
        double r4 = topdown2(a - 25, b - 75, memo);

        return memo[a][b] = 0.25 * (r1 + r2 + r3 + r4);
    }

    public double mySol_fail(int n) {
        return 0.25 * (topdown_a(n, n) + topdown_a_b(n, n));
    }

    public int topdown_a(int a, int b) {
        if (b <= 0) return Integer.MAX_VALUE;

        if (a <= 0) return 1;

        int r1 = topdown_a(a - 100, b - 0);
        int r2 = topdown_a(a - 75, b - 25);
        int r3 = topdown_a(a - 50, b - 50);
        int r4 = topdown_a(a - 25, b - 75);

        return 0;
    }

    public double topdown_a_b(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        
        if (a <= 0 || b <= 0) return 0;

        return topdown_a_b(a - 100, b - 0)
            + topdown_a_b(a - 75, b - 25)
            + topdown_a_b(a - 50, b - 50)
            + topdown_a_b(a - 25, b - 75);
    }
}