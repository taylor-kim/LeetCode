class Solution {
    public int binaryGap(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        int ans = 0;

        while (n > 0) {
            while (n > 0 && (n & 1) == 0) {
                n /= 2;
            }

            n /= 2;

            int count = 0;

            while (n > 0 && (n & 1) == 0) {
                n /= 2;
                count++;
            }

            if ((n & 1) == 1) {
                ans = Math.max(ans, count + 1);
            }
        }

        return ans;
    }
}