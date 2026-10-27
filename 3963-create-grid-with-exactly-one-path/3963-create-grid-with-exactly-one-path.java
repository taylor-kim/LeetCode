class Solution {
    public String[] createGrid(int m, int n) {
        return mySol(m, n);
    }

    public String[] mySol(int m, int n) {
        String[] ans = new String[m];
        Arrays.fill(ans, "");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == n - 1) {
                    ans[i] += ".";
                } else {
                    ans[i] += "#";
                }
            }
        }

        return ans;
    }
}