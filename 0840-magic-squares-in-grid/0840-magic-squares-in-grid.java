class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        return mySol(grid);
    }

    public int mySol(int[][] grid) {
        int ans = 0;

        int m = grid.length;
        int n = grid[0].length;

        int[][] pSumRows = new int[m][n + 1];
        int[][] pSumCols = new int[n][m + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pSumRows[i][j + 1] = pSumRows[i][j] + grid[i][j];
                pSumCols[j][i + 1] = pSumCols[j][i] + grid[i][j];
            }
        }

        for (int i = 0; i < m - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                int row1 = pSumRows[i][j + 3] - pSumRows[i][j];
                int row2 = pSumRows[i + 1][j + 3] - pSumRows[i + 1][j];
                int row3 = pSumRows[i + 2][j + 3] - pSumRows[i + 2][j];

                int col1 = pSumCols[j][i + 3] - pSumCols[j][i];
                int col2 = pSumCols[j + 1][i + 3] - pSumCols[j + 1][i];
                int col3 = pSumCols[j + 2][i + 3] - pSumCols[j + 2][i];

                int dig1 = grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2];
                int dig2 = grid[i + 2][j] + grid[i + 1][j + 1] + grid[i][j + 2];

                if (row1 == row2 
                    && row1 == row3
                    && row1 == col1
                    && row1 == col2
                    && row1 == col3
                    && row1 == dig1
                    && row1 == dig2
                ) {
                    ans++;
                }
            }
        }

        return ans;
    }
}