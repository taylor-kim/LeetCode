class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        return mySol_space_opt(grid);
    }

    public int mySol_space_opt(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] counters = new int[2][n + 1];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            int[] topLeft = {0, 0};

            for (int j = 0; j < n; j++) {
                char c = grid[i][j];

                int[] nextTopLeft = {0, 0};

                for (int k = 0; k < counters.length; k++) {
                    int[] counter = counters[k];

                    nextTopLeft[k] = counter[j + 1];
                    counter[j + 1] = counter[j + 1] + counter[j] - topLeft[k];
                }

                if (c == 'X') {
                    counters[0][j + 1]++;
                } else if (c == 'Y') {
                    counters[1][j + 1]++;
                }

                if (counters[0][j + 1] > 0 && (counters[0][j + 1] == counters[1][j + 1])) {
                    ans++;
                }

                topLeft = nextTopLeft;
            }
        }

        // for (int[] row : counters[0]) {
        //     System.out.println(Arrays.toString(row));
        // }

        // System.out.println("");

        // for (int[] row : counters[1]) {
        //     System.out.println(Arrays.toString(row));
        // }

        return ans;
    }

    public int mySol(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] counters = new int[2][m + 1][n + 1];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];

                for (int[][] counter : counters) {
                    counter[i + 1][j + 1] = counter[i][j + 1] + counter[i + 1][j] - counter[i][j];
                }

                if (c == 'X') {
                    counters[0][i + 1][j + 1]++;
                } else if (c == 'Y') {
                    counters[1][i + 1][j + 1]++;
                }

                if (counters[0][i + 1][j + 1] > 0 && (counters[0][i + 1][j + 1] == counters[1][i + 1][j + 1])) {
                    ans++;
                }
            }
        }

        // for (int[] row : counters[0]) {
        //     System.out.println(Arrays.toString(row));
        // }

        // System.out.println("");

        // for (int[] row : counters[1]) {
        //     System.out.println(Arrays.toString(row));
        // }

        return ans;
    }
}