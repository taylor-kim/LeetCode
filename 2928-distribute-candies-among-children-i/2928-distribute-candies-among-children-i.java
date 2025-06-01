class Solution {
    public int distributeCandies(int n, int limit) {
        return mySol(n, limit);
    }

    public int mySol(int n, int limit) {
        int ans = 0;

        int max = Math.min(n, limit);

        for (int i = 0; i <= Math.min(n, limit); i++) {
            for (int j = 0; j <= Math.min(n - i, limit); j++) {
                int k = n - i - j;

                if (k >= 0 && k <= limit) {
                    ans++;
                }
            }
        }

        return ans;
    }
}