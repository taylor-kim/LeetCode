class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        return mySol(grid, health);
    }

    public boolean mySol(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[2] - a[2];
        });

        pq.add(new int[] {0, 0, health - grid.get(0).set(0, -1)});

        int[][] dirs = {
            {0, 1}, {0, -1}, {1, 0}, {-1 , 0}
        };

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int y = data[0];
            int x = data[1];
            int h = data[2];

            if (y == m - 1 && x == n - 1) {
                return h >= 1;
            }

            for (int[] dir : dirs) {
                int ny = y + dir[0];
                int nx = x + dir[1];
                
                if (ny >= 0 && ny < m && nx >= 0 && nx < n && grid.get(ny).get(nx) >= 0) {
                    int safety = grid.get(ny).set(nx, -1);

                    pq.add(new int[] {ny, nx, h - safety});
                }
            }
        }

        return false;
    }
}