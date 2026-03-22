class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        return mySol(mat, target);
    }

    public boolean official(int[][] mat, int[][] target) {
        int n = mat.length;

        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < (n + 1) / 2; j++) {
                    int temp = mat[i][j];

                    mat[i][j] = mat[n - 1 - j][i];

                    mat[n - 1 - j][i] = mat[n - 1 - i][n - 1 - j];

                    mat[n - 1 - i][n - 1 - j] = mat[j][n - 1 - i];
                    
                    mat[j][n - 1 - i] = temp;
                }
            }

            // [0,1,1,0,1]
            // [1,0,0,1,2]
            // [0,1,0,1,1]
            // [0,0,1,0,1]
            // [0,0,1,0,1]

            if (isEquals(mat, target)) return true;
        }

        return false;
    }

    private boolean isEquals(int[][] mat, int[][] target) {
        int n = mat.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) return false;
            }
        }

        return true;
    }

    public boolean mySol(int[][] mat, int[][] target) {
        int n = mat.length;
        int count = n;

        int point = 0;

        while (count > 0) {
            if (!check(point, point, count, mat, target)) return false;

            point++;
            count -= 2;
        }

        return true;
    }
    
    private boolean check(int y, int x, int size, int[][] mat, int[][] target) {
        String circles1 = getCircles(y, x, size, mat);
        String circles2 = getCircles(y, x, size, target);

        if (size == 1) {
            return circles1.equals(circles2);
        }

        // return (circles1 + circles1).contains(circles2);

        int index = (circles1 + circles1).indexOf(circles2);

        // System.out.println(circles1 + circles1);
        // System.out.println(circles2);
        // System.out.println(index);

        return index >= 0 && ((index) % (size - 1) == 0);
    }

    private String getCircles(int y, int x, int size, int[][] mat) {
        StringBuilder sb = new StringBuilder();

        if (size == 1) {
            sb.append(mat[y][x]);

            return sb.toString();
        }

        int[][] dirs = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        int dir = 0;

        while (sb.length() < (2 * size) + ((size - 2) * 2)) {
            sb.append(mat[y][x]);
            mat[y][x] -= 2;

            int ny = y + dirs[dir][0];
            int nx = x + dirs[dir][1];

            if (ny < 0 || nx < 0 || ny >= mat.length || nx >= mat.length || mat[ny][nx] < 0) {
                dir = (dir + 1) % dirs.length;

                ny = y + dirs[dir][0];
                nx = x + dirs[dir][1];
            }

            y = ny;
            x = nx;
        }

        return sb.toString();
    }
}