class Solution {
    public int countSquares(int[][] matrix) {
        return mySol_space_opt(matrix);
    }

    public int mySol_space_opt(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] dp = new int[n + 1];

        int ans = 0;

        for (int i = 0; i < m; i++) {
            int topLeft = 0;

            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    int nextTopLeft = dp[j + 1];
                    
                    dp[j + 1] = Math.min(topLeft, Math.min(dp[j], dp[j + 1])) + 1;

                    ans += dp[j + 1];

                    topLeft = nextTopLeft;
                } else {
                    dp[j + 1] = 0;
                }
            }
        }

        return ans;
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