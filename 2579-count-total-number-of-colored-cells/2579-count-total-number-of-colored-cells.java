class Solution {
    public long coloredCells(int n) {
        return mySol(n);
    }

    public long mySol(int n) {
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[] {n, n});

        Set<Long> visit = new HashSet();
        visit.add(getKey(n, n));

        int ans = 0;

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

                    if (visit.add(getKey(ny, nx))) {
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