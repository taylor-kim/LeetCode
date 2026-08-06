class Solution {
    public int smallestNumber(int n, int t) {
        return try_20260806(n, t);
    }

    public int try_20260806(int n, int t) {
        int ans = n;

        while (product(ans) % t != 0) {
            ans++;
        }

        return ans;
    }

    private int product(int n) {
        int p = 1;

        while (n > 0) {
            int d = n % 10;
            n /= 10;
            p *= d;
        }

        return p;
    }
}