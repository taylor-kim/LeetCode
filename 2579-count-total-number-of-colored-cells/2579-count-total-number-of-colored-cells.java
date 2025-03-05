class Solution {
    public long coloredCells(int n) {
        return official_math(n);
    }

    public long official_math(int n) {
        return 1 + (long) n * (n - 1) * 2;
    }

    public long mySol2(int n) {
        long ans = 1;
        int time = 1;

        while (time < n) {
            ans += time++ * 4;
        }

        return ans;
    }

    public long mySol(int n) {
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[] {n, n});

        Set<Long> visit = new HashSet();
        // visit.add(getKey(n, n));

        boolean[][] used = new boolean[2 * n + 1][2 * n + 1];
        used[n][n] = true;

        long ans = 0;

        int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int y = queue.peek()[0];
                int x = queue.poll()[1];

                for (int[] dir : dirs) {
                    int ny = y + dir[0];
                    int nx = x + dir[1];

                    if (!used[ny][nx]) {
                        used[ny][nx] = true;
                        queue.add(new int[] {ny, nx});
                    }
                }

                ans++;
            }
            n--;

            if (n == 0) break;
        }

        return ans;
    }

    private long getKey(int y, int x) {
        long key = y * (long)Math.pow(10, 6);

        return key + x;
    }
}