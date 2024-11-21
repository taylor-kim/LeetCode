class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        return mySol(m, n, guards, walls);
    }

    public int mySol(int m, int n, int[][] guards, int[][] walls) {
        int[][] mat = new int[m][n];

        for (int[] wall : walls) {
            mat[wall[0]][wall[1]] = 2;
        }

        for (int[] guard : guards) {
            int y = guard[0];
            int x = guard[1];

            mat[y][x] = 1;

            for (int r = y - 1; r >= 0 && (mat[r][x] == 0 || mat[r][x] == 4); r--) {
                mat[r][x] = 3;
            }

            for (int r = y + 1; r < m && (mat[r][x] == 0 || mat[r][x] == 4); r++) {
                mat[r][x] = 3;
            }

            for (int c = x - 1; c >= 0 && (mat[y][c] == 0 || mat[y][c] == 3); c--) {
                mat[y][c] = 4;
            }

            for (int c = x + 1; c < n && (mat[y][c] == 0 || mat[y][c] == 3); c++) {
                mat[y][c] = 4;
            }
        }

        // for (int[] row : mat) {
        //     System.out.println(Arrays.toString(row));
        // }

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) ans++;
            }
        }

        return ans;
    }
}