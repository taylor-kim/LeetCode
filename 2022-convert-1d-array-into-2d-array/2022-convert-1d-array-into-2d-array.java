class Solution {
    public int[][] construct2DArray(int[] original, int m, int n) {
        return mySol(original, m, n);
    }

    public int[][] mySol(int[] original, int m, int n) {
        if (original.length != m * n) return new int[0][0];

        int[][] ans = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = original[n * i + j];
            }
        }

        return ans;
    }
}