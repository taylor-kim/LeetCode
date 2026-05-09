class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        return mySol(grid, k);
    }

    public int[][] mySol(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int min = Math.min(m, n);

        for (int i = 0; i < min / 2; i++) {
            rotate(grid, i, i, m - (i * 2), n - (i * 2), k);
        }

        // for (int[] row : grid) {
        //     System.out.println(Arrays.toString(row));
        // }

        return grid;
    }

    private void rotate(int[][] grid, int top, int left, int m, int n, int k) {
        List<Integer> list = new LinkedList();

        fillList(grid, top, left, m, n, list);

        int modK = k % list.size();

        for (int i = 0; i < modK; i++) {
            list.add(list.remove(0));
        }

        fillGrid(grid, top, left, m, n, list);
    }

    private void fillList(int[][] grid, int top, int left, int m, int n, List<Integer> list) {
        int[][] dirs = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
        };

        int y = top;
        int x = left;
        int totalSize = 2 * (m + n) - 4;

        int dir = 0;

        while (list.size() < totalSize) {
            list.add(grid[y][x]);
            // System.out.println("list:%s, totalSize:%d".formatted(list, totalSize));

            int maxTry = 3;

            while (!canGo(grid, y, x, m, n, top, left, dirs, dir) && maxTry-- > 0) {
                dir = (dir + 1) % dirs.length;
            }

            y = y + dirs[dir][0];
            x = x + dirs[dir][1];
        }
    }

    private void fillGrid(int[][] grid, int top, int left, int m, int n, List<Integer> list) {
        int[][] dirs = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
        };

        int y = top;
        int x = left;

        int dir = 0;

        while (list.size() > 0) {
            // System.out.println("list:%s, y:%d, x:%d".formatted(list, y, x));
            grid[y][x] = list.remove(0);

            int maxTry = 3;

            while (!canGo(grid, y, x, m, n, top, left, dirs, dir) && maxTry-- > 0) {
                dir = (dir + 1) % dirs.length;
            }

            y = y + dirs[dir][0];
            x = x + dirs[dir][1];
        }
    }

    private boolean canGo(int[][] grid, int y, int x, int m, int n, int top, int left, int[][] dirs, int dir) {
        int nextY = y + dirs[dir][0];
        int nextX = x + dirs[dir][1];

        if (nextY < top || nextY >= top + m || nextX < left || nextX >= left + n) return false;

        return true;
    }
}