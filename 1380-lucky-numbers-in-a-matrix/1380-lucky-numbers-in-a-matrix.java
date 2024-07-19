class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        return official_greedy(matrix);
    }

    public List<Integer> official_greedy(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int rowMinMax = Integer.MIN_VALUE;
        int colMaxMin = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            int rowMin = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                rowMin = Math.min(rowMin, matrix[i][j]);
            }

            rowMinMax = Math.max(rowMinMax, rowMin);
        }

        for (int i = 0; i < n; i++) {
            int colMax = Integer.MIN_VALUE;
            for (int j = 0; j < m; j++) {
                colMax = Math.max(colMax, matrix[j][i]);
            }

            colMaxMin = Math.min(colMaxMin, colMax);
        }

        if (rowMinMax == colMaxMin) {
            return Arrays.asList(rowMinMax);
        }

        return new ArrayList();
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