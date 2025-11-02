class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        return mySol_2024(m, n, guards, walls);
    }

    public int mySol_2024(int m, int n, int[][] guards, int[][] walls) {
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

    public int mySol2(int m, int n, int[][] guards, int[][] walls) {
        int[][] mat = new int[m][n];

        for (int[] p : guards) {
            mat[p[0]][p[1]] = 1;
        }

        for (int[] p : walls) {
            mat[p[0]][p[1]] = 2;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    searchGuarded(mat, i, j);
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void searchGuarded(int[][] mat, int i, int j) {
        // if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length) return;
        // if (mat[i][j] == 2 || mat[i][j] < 0) return;

        mat[i][j] = -1;

        for (int r = i - 1; r >= 0; r--) {
            if (mat[r][j] != 2) {
                mat[r][j] = -1;
            } else {
                break;
            }
        }

        for (int r = i + 1; r < mat.length; r++) {
            if (mat[r][j] != 2) {
                mat[r][j] = -1;
            } else {
                break;
            }
        }

        for (int c = j - 1; c >= 0; c--) {
            if (mat[i][c] != 2) {
                mat[i][c] = -1;
            } else {
                break;
            }
        }

        for (int c = j + 1; c < mat[0].length; c++) {
            if (mat[i][c] != 2) {
                mat[i][c] = -1;
            } else {
                break;
            }
        }
    }

    public int mySol_fail(int m, int n, int[][] guards, int[][] walls) {
        int[] lastWalls = new int[n];
        int[] lastGuards = new int[n];

        Arrays.fill(lastWalls, -1);
        Arrays.fill(lastGuards, -1);

        int[][] mat = new int[m][n];

        for (int[] p : guards) {
            mat[p[0]][p[1]] = 1;
        }

        for (int[] p : walls) {
            mat[p[0]][p[1]] = 2;
        }

        Set<Integer> set = new HashSet();

        int ans = 0;

        for (int i = 0; i < m; i++) {
            int lastIndex = -1;
            int g = 0;
            int ng = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 2) {
                    ans += i - lastWalls[j] - 1;
                    lastWalls[j] = i;
                }
            }
        }

        return ans;
    }
}