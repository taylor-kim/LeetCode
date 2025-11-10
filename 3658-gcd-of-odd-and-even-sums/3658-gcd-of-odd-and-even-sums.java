class Solution {
    public int gcdOfOddEvenSums(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        int odd = (2 + (n - 1) * 2) * n / 2;
        int even = (4 + (n - 1) * 2) * n / 2;

        // System.out.println(String.format("%d, %d", odd, even));

        return gcd(odd, even);
    }

    private int gcd(int a, int b) {
        // if (a < b) return gcd(b, a);

        if (a % b == 0) return b;

        return gcd(b, a % b);
    }
}