class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        return official_abracadabra(grid);
    }

    public int official_abracadabra(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        int count = 0;

        for (int i = 0; i + 2 < rows; i++) {
            for (int j = 0; j + 2 < cols; j++) {
                if (abracadabra(grid, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean abracadabra(int[][] grid, int row, int col) {
        String seq = "2943816729438167";
        String seqReversed = "7618349276183492";

        StringBuilder sb = new StringBuilder();
        int[] borderIndices = {0,1,2,5,8,7,6,3};

        for (int i : borderIndices) {
            int num = grid[row + i / 3][col + (i % 3)];

            sb.append(num);
        }

        String border = sb.toString();

        return grid[row][col] % 2 == 0
                && grid[row + 1][col + 1] == 5
                && (
                    seq.contains(border)
                    || seqReversed.contains(border)
                );
    }

    public int mySol_bruteForce(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isMagic(grid, i, j)) {
                    count++;
                }
            }
        }

        // for (int[] row : grid) {
        //     System.out.println(Arrays.toString(row));
        // }

        return count;
    }

    private boolean isMagic(int[][] grid, int y, int x) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (y + 2 >= rows || x + 2 >= cols) {
            return false;
        }

        int[] freq = new int[16];

        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 3; j++) {
                int num = grid[i][j];

                if (freq[num]++ > 0) {
                    return false;
                }
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (freq[i] != 1) {
                return false;
            }
        }

        int targetSum = grid[y][x] + grid[y][x + 1] + grid[y][x + 2];

        for (int i = 0; i < 3; i++) {
            int rowSum = grid[y + i][x] + grid[y + i][x + 1] + grid[y + i][x + 2];

            if (rowSum != targetSum) {
                return false;
            }

            int colSum = grid[y][x + i] + grid[y + 1][x + i] + grid[y + 2][x + i];

            if (colSum != targetSum) {
                return false;
            }
        }

        int d1 = grid[y][x] + grid[y + 1][x + 1] + grid[y + 2][x + 2];

        if (d1 != targetSum) return false;

        int d2 = grid[y][x + 2] + grid[y + 1][x + 1] + grid[y + 2][x];

        if (d2 != targetSum) return false;

        return true;
    }
}