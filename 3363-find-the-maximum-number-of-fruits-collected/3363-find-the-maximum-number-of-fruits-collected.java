class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        return mySol2_by_hint(fruits);
    }

    public int mySol2_by_hint(int[][] fruits) {
        int n = fruits.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans += fruits[i][i];
        }

        ans += topdown(fruits, 0, n - 1, dirs2, new Integer[n][n], true);
        ans += topdown(fruits, n - 1, 0, dirs3, new Integer[n][n], false);

        return ans;
    }

    private int topdown(int[][] fruits, int i, int j, int[][] dirs, Integer[][] memo, boolean top) {
        int n = fruits.length;

        if (i == n - 1 && j == n - 1) return 0;

        if (isInvalid(n, i, j) || !isCorrectArea(i, j, top)) return -1;

        if (memo[i][j] != null) return memo[i][j];

        int ans = 0;

        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            int local = topdown(fruits, ni, nj, dirs, memo, top);

            if (local >= 0) {
                ans = Math.max(ans, local);
            }
        }

        return memo[i][j] = ans + fruits[i][j];
    }

    private boolean isCorrectArea(int y, int x, boolean top) {
        return top ? y < x : y > x;
    }

    public int mySol_fail(int[][] fruits) {
        int n = fruits.length;
        return topdown(fruits, 0, 0, 0, n - 1, n - 1, 0);
    }

    private int[][] dirs1 = { {1, 1}, {1, 0}, {0, 1} };
    private int[][] dirs2 = { {1, -1}, {1, 0}, {1, 1} };
    private int[][] dirs3 = { {-1, 1}, {0, 1}, {1, 1} };

    public int topdown(int[][] mat, int i1, int j1, int i2, int j2, int i3, int j3) {
        int n = mat.length;

        if (i1 == n - 1 && i2 == n - 1 && i3 == n - 1
            && j1 == n - 1 && j2 == n - 1 && j3 == n - 1) {
            // return mat[i1][j1];
            return 0;
        }
        
        if (isInvalid(n, i1, j1)
            || isInvalid(n, i2, j2)
            || isInvalid(n, i3, j3)) {
                return -1;
        }

        int origin1 = mat[i1][j1];
        int origin2 = mat[i2][j2];
        int origin3 = mat[i3][j3];

        mat[i1][j1] = 0;
        mat[i2][j2] = 0;
        mat[i3][j3] = 0;

        int ans = origin1 + origin2 + origin3;

        int localMax = 0;

        for (int[] dir1 : dirs1) {

            if (isInvalid(n, i1 + dir1[0], j1 + dir1[1])) continue;

            for (int[] dir2 : dirs2) {
                if (isInvalid(n, i2 + dir2[0], j2 + dir2[1])) continue;

                for (int[] dir3 : dirs3) {
                    if (isInvalid(n, i3 + dir3[0], j3 + dir3[1])) continue;

                    int local = topdown(mat, 
                    i1 + dir1[0], j1 + dir1[1], 
                    i2 + dir2[0], j2 + dir2[1], 
                    i3 + dir3[0], j3 + dir3[1]);

                    if (local >= 0) {
                        localMax = Math.max(localMax, local);
                    }
                }
            }
        }

        mat[i1][j1] = origin1;
        mat[i2][j2] = origin2;
        mat[i3][j3] = origin3;

        return ans + localMax;
    }

    private boolean isInvalid(int n, int y, int x) {
        return !isValid(n, y, x);
    }

    private boolean isValid(int n, int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }
}