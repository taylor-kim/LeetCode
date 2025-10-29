class Solution {
    public int smallestNumber(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        int ans = 1;

        while (ans < n) {
            ans = (ans << 1) + 1;
        }

        return ans;
    }
}