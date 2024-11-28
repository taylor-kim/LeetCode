class Solution {
    private int[][] dirs = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public int minimumObstacles(int[][] grid) {
        return official_bfs(grid);
    }

    public int official_bfs(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Integer[][] minOst = new Integer[m][n];
        minOst[0][0] = 0;

        Deque<int[]> deque = new LinkedList();
        deque.add(new int[] {0, 0, 0});

        while (!deque.isEmpty()) {
            int[] d = deque.poll();
            int ost = d[0], y = d[1], x = d[2];

            for (int[] delta : dirs) {
                int ny = y + delta[0], nx = x + delta[1];

                if (isIn(grid, ny, nx) && minOst[ny][nx] == null) {
                    if (grid[ny][nx] == 0) {
                        minOst[ny][nx] = ost;
                        deque.addFirst(new int[] {minOst[ny][nx], ny, nx});
                    } else {
                        minOst[ny][nx] = ost + 1;
                        deque.addLast(new int[] {minOst[ny][nx], ny, nx});
                    }

                    if (ny == m - 1 && nx == n - 1) return minOst[ny][nx];
                }
            }
        }

        return minOst[m - 1][n - 1];
    }

    public int my_bfs_as_official(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[] {0, 0});
        grid[0][0] = -1;

        int ans = 0;

        while (!queue.isEmpty()) {
            Queue<int[]> nextQueue = new LinkedList();

            while (!queue.isEmpty()) {
                int y = queue.peek()[0];
                int x = queue.poll()[1];

                if (y == m - 1 && x == n - 1) {
                    return ans;
                }

                for (int[] delta : dirs) {
                    int ny = y + delta[0];
                    int nx = x + delta[1];

                    if (isIn(grid, ny, nx) && grid[ny][nx] != -1) {
                        if (grid[ny][nx] == 0) {
                            queue.add(new int[] {ny, nx});
                        } else {
                            nextQueue.add(new int[] {ny, nx});
                        }

                        grid[ny][nx] = -1;
                    }
                }
            }

            if (nextQueue.isEmpty()) break;

            queue = nextQueue;
            ans++;
        }

        return -1;
    }

    public int after_topic(int[][] grid) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            // return a[2] != b[2] ? a[2] - b[2] : grid[a[0]][a[1]] - grid[b[0]][b[1]];
            return a[2] - b[2];
        });

        int m = grid.length;
        int n = grid[0].length;

        pq.add(new int[] {0, 0, 0});
        grid[0][0] = 2;

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

                if (isIn(grid, ny, nx) && grid[ny][nx] != 2) {
                    pq.add(new int[] {ny, nx, cost + grid[ny][nx]});
                    grid[ny][nx] = 2;
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