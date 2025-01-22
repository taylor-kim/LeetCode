class Solution {
    public int[][] highestPeak(int[][] isWater) {
        return mySol(isWater);
    }

    public int[][] mySol(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] ans = new int[m][n];

        Queue<int[]> queue = new LinkedList();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    queue.add(new int[] {i, j});
                }
            }
        }

        int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };

        int height = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int y = queue.peek()[0];
                int x = queue.poll()[1];

                // System.out.println(String.format("y:%d, x:%d", y, x));

                for (int i = 0, set = 0; i < dirs.length && set < 4; i++) {
                    int ny = y + dirs[i][0];
                    int nx = x + dirs[i][1];

                    if (ny >= 0 && nx >= 0 && ny < m && nx < n && mat[ny][nx] == 0 && ans[ny][nx] == 0) {
                        // System.out.println(String.format("y:%d, x:%d, ny:%d, nx:%d", y, x, ny, nx));
                        set++;
                        mat[ny][nx] = -mat[ny][nx];
                        queue.add(new int[] {ny, nx});
                        ans[ny][nx] = height;
                    }
                }
            }

            height++;
        }

        return ans;
    }
}