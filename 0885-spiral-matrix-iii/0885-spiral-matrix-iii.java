class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        return mySol(rows, cols, rStart, cStart);
    }

    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] mySol(int rows, int cols, int rStart, int cStart) {
        int[][] ans = new int[rows][cols];

        int y = rStart;
        int x = cStart;

        int number = 1;
        int dist = 1;
        int direction = 0;

        int index = 0;

        int loop = 0;

        int[][] ans2 = new int[rows * cols][2];

        while (number <= rows * cols) {
            if (ans[y][x] == 0) {
                ans[y][x] = number++;

                ans2[index++] = new int[] {y, x};
            }

            int ny = y + dirs[direction][0];
            int nx = x + dirs[direction][1];

            int diff = Math.max(Math.abs(ny - rStart), Math.abs(nx - cStart));

            if (isIn(ny, nx, rows, cols) && diff <= dist) {
                y = ny;
                x = nx;

                if (diff == dist && ans[ny][nx] != 0) {
                    dist++;
                }
            } else {
                direction = (direction + 1) % 4;
            }
        }

        // for (int[] row : ans) {
        //     System.out.println(Arrays.toString(row));
        // }

        return ans2;
    }

    private boolean isIn(int y, int x, int rows, int cols) {
        return y >= 0 && x >= 0 && y < rows && x < cols;
    }
}