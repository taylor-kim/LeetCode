class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        return mySol_fail(mat, threshold);
    }

    public int mySol_gpt(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        long[][] prefix = new long[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i + 1][j + 1] =
                        mat[i][j]
                        + prefix[i][j + 1]
                        + prefix[i + 1][j]
                        - prefix[i][j];
            }
        }

        int ans = 0;

        out: for (int len = 1; len <= Math.min(m, n); len++) {
            for (int i = 0; i + len <= m; i++) {
                for (int j = 0; j + len <= n; j++) {
                    long sum =
                            prefix[i + len][j + len]
                            - prefix[i][j + len]
                            - prefix[i + len][j]
                            + prefix[i][j];

                    if (sum <= threshold) {
                        ans = len;
                        continue out;
                    }
                }
            }
        }

        return ans;
    }

    public int mySol_fail(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        long[][] rowSum = new long[m][n + 1];
        long[][] colSum = new long[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j + 1] = rowSum[i][j] + mat[i][j];
                colSum[i + 1][j] = colSum[i][j] + mat[i][j];
            }
        }

        int ans = 0;

        // for (int[] row : rowSum) {
        //     System.out.println(Arrays.toString(row));
        // }

        // for (int[] row : colSum) {
        //     System.out.println(Arrays.toString(row));
        // }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long sum = 0;
                int limit = Math.min(m, n);
                int maxPossibleLen = Math.min(m - i, n - j);

                for (int length = 1; length <= maxPossibleLen; length++) {
                    long r = rowSum[i + length - 1][j + length] - rowSum[i + length - 1][j];
                    long c = colSum[i + length][j + length - 1] - colSum[i][j + length - 1];

                    sum += r;
                    sum += c;
                    sum -= mat[i + length - 1][j + length - 1];

                    // System.out.println("length:%d, i:%d, j:%d, sum:%d, r:%d, c:%d".formatted(length, i, j, sum, r, c));

                    if (sum <= threshold) {
                        ans = Math.max(ans, length);
                    } else {
                        break;
                    }
                }
            }
        }

        return ans;
    }
}