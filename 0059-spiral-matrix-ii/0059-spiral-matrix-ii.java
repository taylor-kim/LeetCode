class Solution {
    public int[][] generateMatrix(int n) {
        return try_20241112(n);
    }

    public int[][] try_20241112(int n) {
        int[][] dirs = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
        };

        int[][] ans = new int[n][n];

        int y = 0;
        int x = 0;

        int num = 1;
        int dir = 0;
        int size = n * n;

        while (num <= size) {
            if (y < 0 || x < 0 || y >= n || x >= n || ans[y][x] != 0) {
                dir = (dir + 1) % dirs.length;

                y += dirs[dir][0];
                x += dirs[dir][1];
            }

            ans[y][x] = num++;

            int ny = y + dirs[dir][0];
            int nx = x + dirs[dir][1];

            if (ny < 0 || nx < 0 || ny >= n || nx >= n || ans[ny][nx] != 0) {
                dir = (dir + 1) % dirs.length;

                ny = y + dirs[dir][0];
                nx = x + dirs[dir][1];
            }

            y = ny;
            x = nx;
        }

        return ans;
    }
}