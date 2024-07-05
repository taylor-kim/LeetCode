class Solution {
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(helper(red, blue), helper(blue, red));
    }

    public int helper(int red, int blue) {
        int ans = 0;
        int count = 1;

        while (true) {
            if (count % 2 == 1) {
                if (red >= count) red -= count;
                else break;
            } else {
                if (blue >= count) blue -= count;
                else break;
            }
            ans++;
            count++;
        }

        return ans;
    }
}