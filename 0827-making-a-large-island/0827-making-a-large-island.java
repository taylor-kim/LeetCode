class Solution {
    public int largestIsland(int[][] grid) {
        return mySol(grid);
    }

    public int mySol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        UnionFind uf = new UnionFind(m * n);

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int parent = i * n + j;
                    dfs(grid, parent, i, j, uf);
                    ans = Math.max(ans, uf.getCount(parent));
                }
            }
        }

        int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int sum = 1;
                    Set<Integer> groups = new HashSet();

                    for (int[] d : dirs) {
                        int ny = i + d[0];
                        int nx = j + d[1];

                        if (isValid(grid, ny, nx) && grid[ny][nx] != 0) {
                            groups.add(uf.find(ny * n + nx));
                        }
                    }

                    for (int group : groups) {
                        sum += uf.getCount(group);
                    }

                    ans = Math.max(ans, sum);
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] grid, int parent, int row, int col, UnionFind uf) {
        if (!isValid(grid, row, col) || grid[row][col] != 1) return;

        grid[row][col] = -grid[row][col];

        int index = row * grid[0].length + col;

        uf.merge(parent, index);

        dfs(grid, parent, row + 1, col, uf);
        dfs(grid, parent, row - 1, col, uf);
        dfs(grid, parent, row, col + 1, uf);
        dfs(grid, parent, row, col - 1, uf);
    }

    private boolean isValid(int[][] grid, int row, int col) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length;
    }

    public class UnionFind {
        int[] parents;
        int[] counts;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            counts = new int[n];
            ranks = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                counts[i] = 1;
            }
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }

        public void merge(int a, int b) {
            a = find(a);
            b = find(b);

            if (a != b) {
                if (ranks[a] > ranks[b]) {
                    parents[b] = a;
                    counts[a] += counts[b];
                } else if (ranks[a] < ranks[b]) {
                    parents[a] = b;
                    counts[b] += counts[a];
                } else {
                    parents[b] = a;
                    ranks[a]++;
                    counts[a] += counts[b];
                }
            }
        }

        public int getCount(int a) {
            return counts[find(a)];
        }
    }
}