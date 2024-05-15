class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        return official_bfs_bs(grid);
    }

    private int[][] dirs = {
        {0, 1}
        , {0, -1}
        , {1, 0}
        , {-1, 0}
    };

    public int official_bfs_bs(List<List<Integer>> grid) {
        int n = grid.size();

        Queue<int[]> queue = new LinkedList();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    grid.get(i).set(j, 0);
                    queue.add(new int[] {i, j});
                } else {
                    grid.get(i).set(j, -1);
                }
            }
        }

        int maxSafeness = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (!queue.isEmpty()) {
                int[] pos = queue.poll();
                int r = pos[0];
                int c = pos[1];
                int safeness = grid.get(r).get(c);

                maxSafeness = Math.max(maxSafeness, safeness);

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (isValidCell(n, nr, nc) && grid.get(nr).get(nc) == -1) {
                        queue.add(new int[] {nr, nc});
                        grid.get(nr).set(nc, safeness + 1);
                    }
                }
            }
        }

        int lo = 0;
        int hi = maxSafeness;
        int ans = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (isValidSafeness(grid, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean isValidCell(int n, int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    private boolean isValidSafeness(List<List<Integer>> grid, int minSafeness) {
        int n = grid.size();

        if (grid.get(0).get(0) < minSafeness || grid.get(n - 1).get(n - 1) < minSafeness) {
            return false;
        }

        Queue<int[]> queue = new LinkedList();

        boolean[][] visit = new boolean[n][n];
        visit[0][0] = true;

        queue.add(new int[] {0, 0});

        while (!queue.isEmpty()) {
            int r = queue.peek()[0];
            int c = queue.poll()[1];

            if (r == n - 1 && c == n - 1) {
                return true;
            }

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (isValidCell(n, nr, nc) && !visit[nr][nc] && grid.get(nr).get(nc) >= minSafeness) {
                    visit[nr][nc] = true;
                    queue.add(new int[] {nr, nc});
                }
            }
        }

        return false;
    }
}