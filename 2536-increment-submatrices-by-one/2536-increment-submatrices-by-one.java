class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        return editorial(n, queries);
    }

    public int[][] editorial(int n, int[][] queries) {
        int[][] diffArray = new int[n + 1][n + 1];

        for (int[] q : queries) {
            int r1 = q[0];
            int c1 = q[1];
            int r2 = q[2];
            int c2 = q[3];

            diffArray[r1][c1] += 1;
            diffArray[r2 + 1][c1] -= 1;
            diffArray[r1][c2 + 1] -= 1;
            diffArray[r2 + 1][c2 + 1] += 1;
        }

        int[][] mat = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int top = i == 0 ? 0 : mat[i - 1][j];
                int left = j == 0 ? 0 : mat[i][j - 1];
                int topLeft = (i == 0 || j == 0) ? 0 : mat[i - 1][j - 1];

                mat[i][j] = diffArray[i][j] + top + left - topLeft;
            }
        }

        return mat;
    }

    public int[][] mySol(int n, int[][] queries) {
        int[][] diffArray = new int[n][n];

        for (int[] q : queries) {
            for (int c = q[1]; c <= q[3]; c++) {
                diffArray[q[0]][c] += 1;

                if (q[2] + 1 < n) {
                    diffArray[q[2] + 1][c] -= 1;
                }
            }
        }

        for (int c = 0; c < n; c++) {
            for (int r = 1; r < n ;r++) {
                diffArray[r][c] += diffArray[r - 1][c];
            }
        }

        return diffArray;
    }
}