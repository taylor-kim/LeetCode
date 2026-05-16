class Solution {
    public boolean hasValidPath(int[][] grid) {
        return try_uf(grid);
    }
    
    public boolean try_uf(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        UnionFind uf = new UnionFind(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && canJoinTop(grid[i][j], grid[i - 1][j])) {
                    uf.merge(i * n + j, (i - 1) * n + j);
                }

                if (j > 0 && canJoinLeft(grid[i][j], grid[i][j - 1])) {
                    uf.merge(i * n + j, i * n + j - 1);
                }
            }
        }

        return uf.find(0) == uf.find(m * n - 1);
    }

    private boolean canJoinTop(int current, int prev) {
        if (current == 1) {
            return false;
        } else if (current == 2) {
            return prev == 2 || prev == 3 || prev == 4;
        } else if (current == 3) {
            return false;
        } else if (current == 4) {
            return false;
        } else if (current == 5) {
            return prev == 2 || prev == 3 || prev == 4;
        } else {
            return prev == 2 || prev == 3 || prev == 4;
        }
    }

    private boolean canJoinLeft(int current, int prev) {
        if (current == 1) {
            return prev == 1 || prev == 4 || prev == 6;
        } else if (current == 2) {
            return false;
        } else if (current == 3) {
            return prev == 1 || prev == 4 || prev == 6;
        } else if (current == 4) {
            return false;
        } else if (current == 5) {
            return prev == 1 || prev == 4 || prev == 6;
        } else {
            return false;
        }
    }

    class UnionFind {
        private int[] parents;
        private int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                ranks[i] = 0;
            }
        }

        private int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }

        private void merge(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) return;

            if (ranks[a] > ranks[b]) {
                parents[b] = a;
            } else if (ranks[a] < ranks[b]) {
                parents[a] = b;
            } else {
                parents[b] = a;
                ranks[a]++;
            }
        }
    }
}