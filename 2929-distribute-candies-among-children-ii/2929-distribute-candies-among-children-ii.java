class Solution {
    public long distributeCandies(int n, int limit) {
        return official_enumeration(n, limit);
    }

    public long official_enumeration(int n, int limit) {
        long ans = 0;

        for (int i = 0; i <= Math.min(n, limit); i++) {
            int remain = n - i;
            if (remain > 2 * limit) continue;

            // int second = 0; (0 ~ Math.min(remain, limit));
            int secondMin = Math.max(0, remain - limit);
            int secondMax = Math.min(remain, limit);

            ans += secondMax - secondMin + 1;
        }

        return ans;
    }

    public long mySol_tle(int n, int limit) {
        long ans = 0;

        for (long i = 0; i <= Math.min(n, limit); i++) {
            for (long j = 0; j <= Math.min(n - i, limit); j++) {
                long k = n - i - j;

                if (k >= 0 && k <= limit) {
                    ans++;
                }
            }
        }

        return ans;
    }
}