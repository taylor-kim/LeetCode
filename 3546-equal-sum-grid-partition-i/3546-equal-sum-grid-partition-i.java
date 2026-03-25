class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        return mySol(grid);
    }

    public boolean mySol(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long total = 0l;

        long[] rowSum = new long[n];
        long[] colSum = new long[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                total += grid[i][j];
                rowSum[i] += grid[i][j];
                colSum[j] += grid[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            if (total - rowSum[i] == rowSum[i]) return true;

            if (i + 1 < n) {
                rowSum[i + 1] += rowSum[i];
            }
        }

        for (int j = 0; j < m; j++) {
            if (total - colSum[j] == colSum[j]) return true;

            if (j + 1 < m) {
                colSum[j + 1] += colSum[j];
            }
        }

        return false;
    }
}