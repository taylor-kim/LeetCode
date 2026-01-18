class Solution {
    public int largestMagicSquare(int[][] grid) {
        return editorial(grid);
    }

    public int editorial(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rowSum = new int[m][n + 1];
        int[][] colSum = new int[m + 1][n];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                rowSum[i][j + 1] = rowSum[i][j] + grid[i][j];
                colSum[i + 1][j] = colSum[i][j] + grid[i][j];
            }
        }

        for (int length = Math.min(m, n); length >= 2; length--) {
            for (int i = 0; i + length <= m; i++) {
                myLabel : for (int j = 0; j + length <= n ; j++) {
                    int base = rowSum[i][j + length] - rowSum[i][j];

                    for (int i1 = i + 1; i1 < i + length; i1++) {
                        if (base != rowSum[i1][j + length] - rowSum[i1][j]) {
                            continue myLabel;
                        }
                    }

                    for (int j2 = j; j2 < j + length; j2++) {
                        if (base != colSum[i + length][j2] - colSum[i][j2]) {
                            continue myLabel;
                        }
                    }

                    int d1 = 0;
                    int d2 = 0;

                    for (int k = 0; k < length; k++) {
                        d1 += grid[i + k][j + k];
                        d2 += grid[i + k][j + length - 1 - k];
                    }

                    if (d1 == base && d2 == base) {
                        return length;
                    }
                }
            }
        }

        return 1;
    }

    public int search(int[][] grid, int r, int c) {
        int limit = Math.min(grid.length, grid[0].length);

        for (int i = r; i < limit; i++) {
            for (int j = c; j < limit; j++) {
                // ans = Math.max(ans, search(grid, i, j));
            }
        }

        return 0;
    }
}