class Solution {
    public int differenceOfSums(int n, int m) {
        return official_mathematical_derivation(n, m);
    }

    public int official_mathematical_derivation(int n, int m) {
        /**
        sum2 = m + 2m + 3m + ... + km 
            = (1 + 2 + ... + k)m
            = k(1 + k)m / 2
         */
        int k = n / m;
        int sum2 = k * (1 + k) * m / 2;

        /**
        sum1 = sum(1,n) - sum2
            = n * (1 + n) / 2 - sum2
         */
        int sum1 = (n * (1 + n) / 2) - sum2;

        // return sum1 - sum2;

        return n * (1 + n) / 2 - k * (1 + k) * m;
    }

    public int mySol(int n, int m) {
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            if (i % m == 0) {
                ans -= i;
            } else {
                ans += i;
            }
        }

        return ans;
    }
}