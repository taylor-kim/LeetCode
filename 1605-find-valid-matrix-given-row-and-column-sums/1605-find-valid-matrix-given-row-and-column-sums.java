class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        return official_time_and_space_opt(rowSum, colSum);
    }

    public int[][] official_time_and_space_opt(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;

        int[][] ret = new int[m][n];

        int i = 0, j = 0;

        while (i < m && j < n) {
            ret[i][j] = Math.min(rowSum[i], colSum[j]);

            rowSum[i] -= ret[i][j];
            colSum[j] -= ret[i][j];

            if (rowSum[i] == 0) {
                i++;
            } else {
                j++;
            }
        }

        return ret;
    }

    public int[][] try_spaceopt_greedy(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;

        int[][] ret = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[i][j] = Math.min(
                    rowSum[i]
                    , colSum[j]
                );

                rowSum[i] -= ret[i][j];
                colSum[j] -= ret[i][j];
            }
        }

        return ret;
    }

    public int[][] official_greedy(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;

        int[] curRowSum = new int[m];
        int[] curColSum = new int[n];

        int[][] ret = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[i][j] = Math.min(
                    rowSum[i] - curRowSum[i]
                    , colSum[j] - curColSum[j]
                );

                curRowSum[i] += ret[i][j];
                curColSum[j] += ret[i][j];
            }
        }

        return ret;
    }

    public int[][] bruteForce_fail(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;

        int[][] matrix = new int[m][n];

        // for (int[] row : matrix) {
        //     Arrays.fill(row, -1);
        // }

        int total = 0;

        for (int num : rowSum) total += num;

        build(matrix, rowSum, colSum, 0, 0, total);

        return matrix;
    }

    private boolean build(int[][] mat, int[] rowSum, int[] colSum, int r, int c, int odd) {
        if (r >= mat.length && c >= mat[0].length && odd == 0) {
            return true;
        }

        if (r >= mat.length || c >= mat[0].length) return false;

        int rMax = rowSum[r];
        int cMax = colSum[c];

        int delta = 0;
        int limit = Math.max(rMax, cMax);

        while (delta <= limit) {
            mat[r][c] = delta;

            rowSum[r] -= delta;
            colSum[c] -= delta;

            if (build(mat, rowSum, colSum, r, c + 1, odd - delta)
                || build(mat, rowSum, colSum, r + 1, c, odd - delta)
                || build(mat, rowSum, colSum, r + 1, c + 1, odd - delta)
            ) {
                return true;
            }

            rowSum[r] += delta;
            colSum[c] += delta;

            delta++;
        }

        mat[r][c] = 0;

        return false;
    }
}