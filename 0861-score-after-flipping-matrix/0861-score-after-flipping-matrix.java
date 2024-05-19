class Solution {
    public int matrixScore(int[][] grid) {
        return official_greedy(grid);
    }

    public int official_greedy(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = 1 - grid[i][j];
                }
            }
        }

        for (int c = 1; c < n; c++) {
            int countOfZero = 0;

            for (int r = 0; r < m; r++) {
                if (grid[r][c] == 0) countOfZero++;
            }

            if (countOfZero > m / 2) {
                for (int r = 0; r < m; r++) {
                    grid[r][c] = 1 - grid[r][c];
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int value = grid[i][j] << (n - j - 1);

                ans += value;
            }
        }

        return ans;
    }

    public int mySol_fail(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] rows = new int[m];
        int[] cols = new int[n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int value = grid[r][c];

                int rowMask = value << (n - c - 1);
                int colMask = value << (m - r - 1);

                rows[r] |= rowMask;
                cols[c] |= colMask;
            }
        }

        int ans = 0;

        for (int r = 0; r < m; r++) {
            ans = Math.max(ans, rows[r]);

            for (int c = 0; c < n; c++) {
                
            }
        }

        // for (int row : rows) {
        //     System.out.println(Integer.toBinaryString(row));
        // }

        return 0;
    }

    private int getSum(int[] rows) {
        int sum = 0;

        for (int row : rows) {
            sum += row;
        }

        return sum;
    }
}