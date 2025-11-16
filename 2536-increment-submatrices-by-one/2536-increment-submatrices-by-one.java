class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        return mySol(n, queries);
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