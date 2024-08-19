class Solution {
    public int minSteps(int n) {
        return official_prime_factorizaion(n);
    }

    public int official_prime_factorizaion(int n) {
        int ans = 0;

        int d = 2;

        while (n > 1) {
            // if d is prime factor, keep dividing
            // n by d until is no longer divisible.
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }

        return ans;
    }

    public int official_bottomup(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1000);

        dp[1] = 0;

        for (int num = 2; num <= n; num++) {
            for (int paste = 1; paste <= num / 2; paste++) {
                if (num % paste == 0) {
                    dp[num] = Math.min(dp[num], dp[paste] + num / paste);
                }
            }
        }

        return dp[n];
    }

    public int mySol(int n) {
        return mySol(n, 1, 0);
    }

    public int mySol(int n, int count, int paste) {
        if (n == count) {
            return 0;
        }

        if (n < count) return 1000;

        // System.out.println(String.format("count:%d, paste:%d", count, paste));

        int ans = 2 + mySol(n, count * 2, count);

        if (paste > 0) {
            ans = Math.min(ans, 1 + mySol(n, count + paste, paste));
        }

        return ans;
    }
}