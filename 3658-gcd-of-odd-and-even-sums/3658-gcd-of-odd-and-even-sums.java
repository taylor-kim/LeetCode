class Solution {
    public int gcdOfOddEvenSums(int n) {
        return mySol_20260715(n);
    }

    public int mySol_20260715(int n) {
        int oddSum = n * (2 * 1 + (n - 1) * 2) / 2;
        int evenSum = n * (2 * 2 + (n - 1) * 2) / 2;

        return gcd2(oddSum, evenSum);
    }

    private int gcd2(int a, int b) {
        if (b == 0) return a;

        return gcd2(b, a % b);
    }

    public int mySol(int n) {
        int odd = (2 + (n - 1) * 2) * n / 2;
        int even = (4 + (n - 1) * 2) * n / 2;

        // System.out.println(String.format("%d, %d", odd, even));

        return gcd(even, odd);
    }

    private int gcd(int a, int b) {
        // if (a < b) return gcd(b, a);

        if (a % b == 0) return b;

        return gcd(b, a % b);
    }
}