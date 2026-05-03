class Solution {
    public void rotate(int[][] matrix) {
        mySol(matrix);
    }

    public void mySol(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            rotate(matrix, i, i, n - (i * 2));
        }
    }

    private void rotate(int[][] matrix, int i, int j, int size) {
        for (int col = 0; col < size - 1; col++) {
            //top => i, need col
            //right => need row, j + size - 1
            //bottom => i + size - 1, need col
            //left = > need row, j

            int v1 = matrix[i][j + col];
            int v2 = matrix[i + col][j + size - 1];
            int v3 = matrix[i + size - 1][j + size - 1 - col];
            int v4 = matrix[i + size - 1 - col][j];

            matrix[i][j + col] = v4;
            matrix[i + col][j + size - 1] = v1;
            matrix[i + size - 1][j + size - 1 - col] = v2;
            matrix[i + size - 1 - col][j] = v3;
        }
    }
}