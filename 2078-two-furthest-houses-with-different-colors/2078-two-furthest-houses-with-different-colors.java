class Solution {
    public int maxDistance(int[] colors) {
        return mySol(colors);
    }

    public int mySol(int[] colors) {
        int n = colors.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                if (colors[i] != colors[j]) {
                    ans = Math.max(ans, j - i);
                }
            }
        }

        return ans;
    }
}