class Solution {
    public int minCost(int[][] grid) {
        return mySol(grid);
    }

    public int mySol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[2] != b[2] ? a[2] - b[2] : (m - a[0] + n - a[1]) - (m - b[0] + n - b[1]);
        });

        queue.add(new int[] {0,0,0});

        int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };

        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            int y = data[0];
            int x = data[1];
            int cost = data[2];

            if (grid[y][x] < 0) {
                // System.out.println(String.format("y:%d, x:%d", y, x));
                continue;
            }

            if (y == grid.length - 1 && x == grid[0].length - 1) {
                return cost;
            }

            int nextD = grid[y][x];

            grid[y][x] = -grid[y][x];

            for (int i = 0; i < dirs.length; i++) {
                int[] d = dirs[i];
                int ny = y + d[0];
                int nx = x + d[1];

                if (ny >= 0 && ny < grid.length && nx >= 0 && nx < grid[0].length && grid[ny][nx] > 0) {
                    int need = i + 1 == nextD ? 0 : 1;
                    queue.add(new int[] {ny, nx, cost + need});
                }
            }
        }

        return -1;
    }
}