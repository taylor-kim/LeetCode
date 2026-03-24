class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        return official_without_division(grid);
    }

    public int[][] official_without_division(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] p = new int[n][m];

        long suffix = 1l;
        long mod = 12345l;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                p[i][j] = (int)suffix;

                suffix = suffix * grid[i][j] % mod;
            }
        }

        long prefix = 1l;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int)(p[i][j] * prefix % mod);

                prefix = prefix * grid[i][j] % mod;
            }
        }

        return p;
    }

    public int[][] mySol_fail(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long[] pMulti = new long[n * m + 1];
        pMulti[0] = 1l;

        long total = 1l;
        long mod = 12345;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int index = m * i + j;

                pMulti[index + 1] = (pMulti[index] * grid[i][j]) % mod;

                total = (total * grid[i][j]) % mod;
            }
        }

        System.out.println(Arrays.toString(pMulti));

        System.out.println("max:%d, total:%d, pMulti:%d".formatted(Long.MAX_VALUE, total, pMulti[pMulti.length - 1]));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int index = m * i + j;

                grid[i][j] = (int)(((total / grid[i][j])) % mod);

                // long prev = pMulti[index];
                // long next = pMulti[pMulti.length - 1] / pMulti[index + 1];

                // System.out.println("prev:%d, next:%d".formatted(prev, next));

                // grid[i][j] = (int)((prev * next) % mod);

                // int a = (int)(prev % mod);
                // int b = (int)(next % mod);
                
                // grid[i][j] = (int)((a * b) % mod);
            }
        }

        return grid;
    }
}