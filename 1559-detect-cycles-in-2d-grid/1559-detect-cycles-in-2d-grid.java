class Solution {
    public boolean containsCycle(char[][] grid) {
        return try_uf(grid);
    }

    public boolean editorial(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        UnionFind uf = new UnionFind(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int current = i * n + j;

                if (i > 0 && grid[i - 1][j] == grid[i][j]) {
                    int top = (i - 1) * n + j;
                    uf.merge(top, current);
                }

                if (j > 0 && grid[i][j - 1] == grid[i][j]) {
                    int left = i * n + j - 1;
                    if (!uf.merge(left, current)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean try_uf(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<UnionFind> ufs = new ArrayList();

        for (int i = 0; i < 26; i++) {
            ufs.add(new UnionFind(m * n));
        }

        UnionFind uf = new UnionFind(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // UnionFind uf = ufs.get(grid[i][j] - 'a');

                int current = i * n + j;
                int parentOfTop = -1;
                int parentOfLeft = -2;

                if (i > 0 && grid[i - 1][j] == grid[i][j]) {
                    int top = (i - 1) * n + j;
                    parentOfTop = uf.find(top);
                    uf.merge(top, current);
                }

                if (j > 0 && grid[i][j - 1] == grid[i][j]) {
                    int left = i * n + j - 1;
                    parentOfLeft = uf.find(left);
                    uf.merge(left, current);
                }

                if (parentOfTop == parentOfLeft) return true;
            }
        }

        return false;
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

        private boolean merge(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) return false;

            if (ranks[a] > ranks[b]) {
                parents[b] = a;
            } else if (ranks[a] < ranks[b]) {
                parents[a] = b;
            } else {
                parents[b] = a;
                ranks[a]++;
            }

            return true;
        }
    }

    public boolean mySol(char[][] grid) {
        Set<Integer> visited = new HashSet();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited.contains(i * grid[0].length + j)) continue;

                if (hasCycle(grid, i, j, i, j, -1, -1, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasCycle(char[][] grid, int startRow, int startCol, int row, int col, int prevRow, int prevCol, int depth,
                            Set<Integer> visited) {

        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return false;

        if (grid[startRow][startCol] != grid[row][col]) return false;

        // if (depth > 0 && startRow == row && startCol == col) {
        //     return true;
        // }

        if (!visited.add(row * grid[0].length + col)) return true;

        int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            
            if (nextRow == prevRow && nextCol == prevCol) continue;

            if (hasCycle(grid, startRow, startCol, nextRow, nextCol, row, col, depth + 1, visited)) {
                return true;
            }
        }

        return false;
    }
}