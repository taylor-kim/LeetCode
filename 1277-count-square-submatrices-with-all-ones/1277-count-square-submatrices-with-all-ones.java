class Solution {
    public int countSquares(int[][] matrix) {
        return mySol(matrix);
    }

    public int mySol(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m + 1][n + 1];

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1])) + 1;

                    ans += dp[i + 1][j + 1];
                }
            }
        }

        return ans;
    }
}