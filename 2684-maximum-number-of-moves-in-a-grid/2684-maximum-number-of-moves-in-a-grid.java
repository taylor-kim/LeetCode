class Solution {
    public int maxMoves(int[][] grid) {
        return mySol(grid);
    }

    public int dfs(int[][] grid) {
        int ans = 0;

        boolean[][] visit = new boolean[grid.length][grid[0].length];

        for (int r = 0; r < grid.length; r++) {
            visit[r][0] = true;
            ans = Math.max(ans, dfs(grid, 0, 0, visit));
        }

        return ans;
    }

    public int dfs(int[][] grid, int r, int c, boolean[][] visit) {
        if (visit[r][c]) return 0;

        visit[r][c] = true;

        int ans = 0;

        for (int delta = -1 ; delta <= 1; delta++) {
            int nextR = r + delta;
            int nextC = c + 1;

            if (nextR < 0 || nextR >= grid.length || nextC >= grid[0].length
                || grid[r][c] < grid[nextR][nextC] || visit[nextR][nextC]) continue;

            ans = Math.max(ans, dfs(grid, nextR, nextC, visit));
        }

        return ans;
    }

    public int mySol(int[][] grid) {
        int ans = 0;

        int m = grid.length;
        int n = grid[0].length;

        Queue<Integer> queue = new LinkedList();
        Set<Integer> visit = new HashSet();

        for (int r = 0; r < m; r++) {
            int index = r * n;
            queue.add(index);
            visit.add(index);
        }

        while (queue.size() > 0) {
            int index = queue.poll();
            int r = index / n;
            int c = index % n;

            ans = Math.max(ans, c);

            if (ans == n) break;

            int val = grid[r][c];

            for (int next = -1; next <= 1; next++) {
                int nextR = r + next;
                int nextC = c + 1;
                int nextIndex = nextR * n + nextC;

                if (nextR < 0 || nextR >= m || nextC >= n) continue;

                if (val < grid[nextR][nextC] && visit.add(nextIndex)) {
                    queue.add(nextIndex);
                }
            }
        }

        return ans;
    }
}