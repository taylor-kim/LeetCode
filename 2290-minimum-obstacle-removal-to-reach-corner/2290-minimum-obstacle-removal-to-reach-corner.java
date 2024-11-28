class Solution {
    private int[][] dirs = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public int minimumObstacles(int[][] grid) {
        return after_topic(grid);
    }

    public int after_topic(int[][] grid) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[2] != b[2] ? a[2] - b[2] : grid[a[0]][a[1]] - grid[b[0]][b[1]];
        });
        Set<Integer> visit = new HashSet();

        int m = grid.length;
        int n = grid[0].length;

        pq.add(new int[] {0, 0, 0});
        visit.add(0);

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int y = data[0];
            int x = data[1];
            int cost = data[2];

            if (y == m - 1 && x == n - 1) {
                return cost;
            }

            for (int[] delta : dirs) {
                int ny = y + delta[0];
                int nx = x + delta[1];

                if (isIn(grid, ny, nx) && visit.add(ny * n + nx)) {
                    pq.add(new int[] {ny, nx, cost + grid[ny][nx]});
                }
            }
        }

        return -1;
    }

    private boolean isIn(int[][] grid, int y, int x) {
        return y >= 0 && x >= 0 && y < grid.length && x < grid[0].length;
    }

    public int mySol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int lo = 0;
        int hi = m * n - 2;

        Integer[][][] memo = new Integer[hi + 1][m][n];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (topdown(grid, 0, 0, mid, memo) < m * n) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private int topdown(int[][] grid, int r, int c, int canRemove, Integer[][][] memo) {
        int m = grid.length;
        int n = grid[0].length;

        if (r == m - 1 && c == n - 1) return 0;

        if (memo[canRemove][r][c] != null) {
            return memo[canRemove][r][c];
        }

        int ans = m * n;

        int remove = grid[r][c];
        grid[r][c] = 2;

        for (int[] delta : dirs) {
            int nr = r + delta[0];
            int nc = c + delta[1];

            if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] < 2) {
                if (canRemove - grid[nr][nc] >= 0) {
                    ans = Math.min(ans, grid[nr][nc] + topdown(grid, nr, nc, canRemove - grid[nr][nc], memo));
                }
            }
        }

        grid[r][c] = remove;

        return ans;
    }
}