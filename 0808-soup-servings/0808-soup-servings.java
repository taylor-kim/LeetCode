class Solution {
    public double soupServings(int n) {
        return trick(n);
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

        double r1 = 0.25 * topdown2(a - 100, b - 0, memo);
        double r2 = 0.25 * topdown2(a - 75, b - 25, memo);
        double r3 = 0.25 * topdown2(a - 50, b - 50, memo);
        double r4 = 0.25 * topdown2(a - 25, b - 75, memo);

        return memo[a][b] = r1 + r2 + r3 + r4;
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