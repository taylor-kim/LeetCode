class Solution {
    public int minSteps(int n) {
        return mySol(n);
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