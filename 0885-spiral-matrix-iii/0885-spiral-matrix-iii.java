class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        return mySol(rows, cols, rStart, cStart);
    }

    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] mySol(int rows, int cols, int rStart, int cStart) {
        int[][] ans = new int[rows * cols][];

        int[][] mat = new int[rows][cols];

        int y = rStart;
        int x = cStart;

        int number = 1;
        int dist = 1;
        int direction = 0;
        int index = 0;
        int changeDir = 0;

        while (index < ans.length) {
            if (mat[y][x] == 0) {
                mat[y][x] = number++;
                ans[index++] = new int[] {y, x};
            }

            for (int k = 0; k < 4; k++) {
                int ny = y + dirs[direction][0];
                int nx = x + dirs[direction][1];

                int diff = Math.max(Math.abs(ny - rStart), Math.abs(nx - cStart));

                if (isIn(ny, nx, rows, cols) && diff <= dist) {
                    y = ny;
                    x = nx;
                    break;
                } else {
                    direction = (direction + 1) % 4;
                    changeDir++;
                    if (changeDir == 4) {
                        dist++;
                        changeDir = 0;
                    }
                }
            }
        }

        return ans;
    }

    private boolean isIn(int y, int x, int rows, int cols) {
        return y >= 0 && x >= 0 && y < rows && x < cols;
    }
}