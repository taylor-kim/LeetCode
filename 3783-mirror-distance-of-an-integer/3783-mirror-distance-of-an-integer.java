class Solution {
    public int mirrorDistance(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        int ans = n;
        int mirror = 0;

        while (n > 0) {
            mirror *= 10;
            mirror += n % 10;
            n /= 10;
        }

        return Math.abs(ans - mirror);
    }
}