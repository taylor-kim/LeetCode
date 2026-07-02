class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        return official_binaryBfs(grid, health);
    }

    public boolean official_binaryBfs(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] costs = new int[m][n];

        for (int[] row : costs) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        costs[0][0] = grid.get(0).get(0);

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[] {costs[0][0], 0, 0});

        int[][] dirs = {
            {0, 1}, {0, -1}, {1, 0}, {-1 , 0}
        };

        while (!deque.isEmpty()) {
            int[] data = deque.pollFirst();
            int cost = data[0];
            int y = data[1];
            int x = data[2];

            if (cost >= health) {
                return false;
            }

            if (y == m - 1 && x == n - 1) return true;

            for (int[] dir : dirs) {
                int ny = y + dir[0];
                int nx = x + dir[1];
                
                if (ny >= 0 && ny < m && nx >= 0 && nx < n) {
                    int nextCost = cost + grid.get(ny).get(nx);

                    if (costs[ny][nx] <= nextCost) continue;

                    costs[ny][nx] = nextCost;

                    if (nextCost - cost == 0) {
                        deque.offerFirst(new int[] {nextCost, ny, nx});
                    } else {
                        deque.offerLast(new int[] {nextCost, ny, nx});
                    }
                }
            }
        }

        return false;
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