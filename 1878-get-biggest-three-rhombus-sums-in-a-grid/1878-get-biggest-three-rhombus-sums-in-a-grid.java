class Solution {
    public int[] getBiggestThree(int[][] grid) {
        return mySol(grid);
    }

    public int[] mySol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int max = (Math.min(m, n) + 1) / 2;

        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int size = 1; size <= max; size++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (size == 1) {
                        treeSet.add(grid[i][j]);
                    } else {
                        topdown(grid, size, 0, Dir.DR, i, j, 0, treeSet);
                    }
                }
            }
        }

        while (treeSet.size() > 3) {
            treeSet.remove(treeSet.first());
        }

        int[] ans = new int[treeSet.size()];

        for (int i = 0; i < ans.length; i++) {
            ans[ans.length - i - 1] = treeSet.first();
            treeSet.remove(treeSet.first());
        }

        return ans;
    }

    private void topdown(int[][] grid, int max, int sum, Dir dir, int y, int x, int length, TreeSet<Integer> treeSet) {
        if (y < 0 || x < 0 || y >= grid.length || x >= grid[0].length) {
            return;
        }

        if (grid[y][x] < 0) {
            treeSet.add(sum);

            if (treeSet.size() > 3) {
                treeSet.remove(treeSet.first());
            }
            return;
        }

        int add = grid[y][x];

        grid[y][x] = -grid[y][x];

        if (++length == max) {
            length = 1;
            dir = Dir.NEXT.get(dir);
        }

        topdown(grid, max, sum + add, dir, y + dir.dy, x + dir.dx, length, treeSet);

        grid[y][x] = -grid[y][x];
    }

    public enum Dir {
        DR(1, 1),
        DL(1, -1),
        TL(-1, -1),
        TR(-1, 1);

        Dir(int dy, int dx) {
            this.dy = dy;
            this.dx = dx;
        }

        private int dy;
        private int dx;

        public static Map<Dir, Dir> NEXT = Map.of(
            DR, DL,
            DL, TL,
            TL, TR,
            TR, DR
        );
    }
}