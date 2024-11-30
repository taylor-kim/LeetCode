class Solution {
    public int minimumTime(int[][] grid) {
        return mySol2(grid);
    }

    private int[][] dirs = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };

    public int mySol2(int[][] grid) {
        int ans = 0;

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });
        Set<Integer> visit = new HashSet();

        pq.add(new int[] {0, 0, 0});
        visit.add(0);

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int time = data[0], y = data[1], x = data[2];

            if (y == grid.length - 1 && x == grid[0].length - 1) {
                return time;
            }

            List<int[]> laters = new ArrayList();
            boolean hasAlreadyVisitedNext = false;

            for (int[] delta : dirs) {
                int ny = y + delta[0], nx = x + delta[1];
                if (!isIn(grid, ny, nx)) continue;

                if (time + 1 >= grid[ny][nx]) {
                    if (visit.add(ny * grid[0].length + nx)) {
                        pq.add(new int[] {time + 1, ny, nx});
                    }
                    hasAlreadyVisitedNext = true;
                } else {
                    laters.add(new int[] {grid[ny][nx], ny, nx});
                }
            }

            if (hasAlreadyVisitedNext) {
                for (int[] later : laters) {
                    if ((later[0] - time) % 2 == 0) {
                        later[0]++;
                    }

                    pq.add(later);
                    visit.add(later[1] * grid[0].length + later[2]);
                }
            }
        }

        return -1;
    }

    private boolean isIn(int[][] grid, int y, int x) {
        return y >= 0 && x >= 0 && y < grid.length && x < grid[0].length;
    }
}