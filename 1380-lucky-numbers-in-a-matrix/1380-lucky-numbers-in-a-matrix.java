class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        return mySol(matrix);
    }

    public List<Integer> mySol(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] minOfRows = new int[m];
        int[] maxOfCols = new int[n];

        Arrays.fill(minOfRows, Integer.MAX_VALUE);
        Arrays.fill(maxOfCols, Integer.MIN_VALUE);

        List<Integer> ans = new ArrayList();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minOfRows[i] = Math.min(minOfRows[i], matrix[i][j]);
                maxOfCols[j] = Math.max(maxOfCols[j], matrix[i][j]);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == minOfRows[i] && matrix[i][j] == maxOfCols[j]) {
                    ans.add(matrix[i][j]);
                }
            }
        }

        return ans;
    }
}