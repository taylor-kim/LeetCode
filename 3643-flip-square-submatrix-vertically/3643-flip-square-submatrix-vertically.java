class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        return mySol(grid, x, y, k);
    }

    public int[][] mySol(int[][] grid, int x, int y, int k) {
        for (int i = x, delta = 0; i < x + k / 2; i++, delta++) {
            int oposite = x + k - delta - 1;

            for (int j = y; j < y + k; j++) {
                int temp = grid[i][j];
                grid[i][j] = grid[oposite][j];
                grid[oposite][j] = temp;
            }
        }

        return grid;
    }
}