class Solution {
    public long gridGame(int[][] grid) {
        return official_prefix_suffix_sum(grid);
    }

    public long official_prefix_suffix_sum(int[][] grid) {
        long firstRowSum = 0;

        for (int num : grid[0]) firstRowSum += num;

        long secondRowSum = 0;
        long minimumSum = Long.MAX_VALUE;

        for (int turnIndex = 0; turnIndex < grid[0].length; turnIndex++) {
            firstRowSum -= grid[0][turnIndex];

            minimumSum = Math.min(minimumSum, Math.max(firstRowSum, secondRowSum));

            secondRowSum += grid[1][turnIndex];
        }

        return minimumSum;
    }

    public long after_official_fail(int[][] grid) {
        int n = grid[0].length;
        long[][] pSum = new long[2][n + 1];
        long[][] sSum = new long[2][n + 1];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < n; j++) {
                pSum[i][j + 1] = pSum[i][j] + grid[i][j];

                int k = n - j - 1;
                sSum[i][k] = sSum[i][k + 1] + grid[i][k];
            }
        }

        long ans = Long.MIN_VALUE;

        long firstMax = 0;
        int bestIndex = 0;

        for (int i = 0; i < n; i++) {
            long top = pSum[0][i + 1] - pSum[0][0];
            long bottom = pSum[1][n] - pSum[1][i];

            if (firstMax < top + bottom) {
                firstMax = top + bottom;
                bestIndex = i;
            }
        }

        long top = pSum[0][n] - pSum[0][bestIndex + 1];
        long bottom = pSum[1][bestIndex] - pSum[1][0];

        System.out.println(String.format("bestIndex:%d, top:%d, bottom:%d", bestIndex, top, bottom));

        ans = Math.max(top, bottom);

        return ans;
    }

    public long mySol_topdown(int[][] grid) {
        return topdown(grid, 0, 0, 0, 0);
    }

    private long topdown(int[][] grid, int r1, int c1, int r2, int c2) {
        if (r1 >= grid.length || c1 >= grid[0].length) {
            return 0l;
        }

        if (r2 >= grid.length || c2 >= grid[0].length) {
            return 0l;
        }

        grid[r1][c1] = -grid[r1][c1];

        long current = 0;

        if (grid[r2][c2] > 0) {
            current += grid[r2][c2];
        }

        long max = 0l;

        max = Math.max(max, topdown(grid, r1 + 1, c1, r2 + 1, c2));
        max = Math.max(max, topdown(grid, r1 + 1, c1, r2, c2 + 1));

        max = Math.max(max, topdown(grid, r1, c1 + 1, r2 + 1, c2));
        max = Math.max(max, topdown(grid, r1, c1 + 1, r2, c2 + 1));

        grid[r1][c1] = grid[r1][c1];

        return max + current;
    }

    public long mySol_fail(int[][] grid) {
        go(grid, getPsum(grid));
        
        return go(grid, getPsum(grid));
    }

    private int[][] getPsum(int[][] grid) {
        int n = grid[0].length;
        int[][] pSum = new int[2][n + 1];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < n; col++) {
                pSum[row][col + 1] = pSum[row][col] + grid[row][col];
            }
        }

        return pSum;
    }

    private int go(int[][] grid, int[][] pSum) {
        int n = grid[0].length;
        int row = 0;
        int col = 0;
        int sum = 0;

        // System.out.println("pSum");
        // for (int i = 0; i < grid.length; i++) {
        //     System.out.println(Arrays.toString(pSum[i]));
        // }

        while (col < n) {
            sum += grid[row][col];
            grid[row][col] = 0;

            if (row == 0) {
                int top = pSum[row][n] - pSum[row][col + 1];
                int bottom = pSum[row + 1][n] - pSum[row + 1][col];

                System.out.println(String.format("top:%d, bottom:%d", top, bottom));

                if (top < bottom) {
                    row++;
                    continue;
                }
            }

            col++;
        }

        // System.out.println(String.format("grid, sum:%d", sum));
        // for (int i = 0; i < grid.length; i++) {
        //     System.out.println(Arrays.toString(grid[i]));
        // }

        return sum;
    }
}