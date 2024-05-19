class Solution {
    public int matrixScore(int[][] grid) {
        return official_greedy_without_modifying(grid);
    }

    public int official_greedy_without_modifying(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int score = (1 << (n - 1)) * m;

        for (int j = 1; j < n; j++) {
            int countSameBits = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == grid[i][0]) {
                    countSameBits++;
                }
            }

            countSameBits = Math.max(countSameBits, m - countSameBits);

            int colScore = (1 << (n - j - 1)) * countSameBits;

            score += colScore;
        }

        return score;
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

            // if (countOfZero > m / 2) {
            if (countOfZero > m - countOfZero) {
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