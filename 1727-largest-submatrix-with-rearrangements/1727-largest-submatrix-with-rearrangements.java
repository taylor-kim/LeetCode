class Solution {
    public int largestSubmatrix(int[][] matrix) {
        return try_20260317(matrix);
    }

    public int try_20260317(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int ans = 0;

        for (int i = 0; i < m; i++) {
            Integer[] cols = new Integer[n];

            for (int j = 0; j < n; j++) {
                int top = i == 0 ? 0 : matrix[i - 1][j];
                matrix[i][j] += matrix[i][j] != 0 ? top : matrix[i][j];

                cols[j] = matrix[i][j];
            }

            Arrays.sort(cols, Comparator.reverseOrder());

            int h = Integer.MAX_VALUE;

            for (int j = 0; j < n && cols[j] > 0; j++) {
                h = Math.min(h, cols[j]);
                ans = Math.max(ans, h * (j + 1));
            }
        }

        return ans;
    }
}