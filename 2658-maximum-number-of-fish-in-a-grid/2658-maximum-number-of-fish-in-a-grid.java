class Solution {
    public int findMaxFish(int[][] grid) {
        return mySol(grid);
    }

    public int official_dfs(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    ans = Math.max(ans, dfs(grid, i, j));
                }
            }
        }

        return ans;
    }

    public int dfs(int[][] grid, int y, int x) {
        if (y < 0 || x < 0 || y >= grid.length || x >= grid[0].length || grid[y][x] <= 0) return 0;

        int current = grid[y][x];

        grid[y][x] = -grid[y][x];

        return current +
            dfs(grid, y + 1, x) +
            dfs(grid, y - 1, x) +
            dfs(grid, y, x + 1) +
            dfs(grid, y, x - 1);
    }

    public int mySol(int[][] grid) {
        int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };

        int m = grid.length;
        int n = grid[0].length;

        UnionFind uf = new UnionFind(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    int index = i * n + j;
                    uf.scores[index] = grid[i][j];
                    grid[i][j] = -grid[i][j];
                    for (int[] d : dirs) {
                        int ny = i + d[0];
                        int nx = j + d[1];

                        if (ny >= 0 && nx >= 0 && ny < m && nx < n && grid[ny][nx] < 0) {
                            int adjIndex = ny * n + nx;
                            uf.merge(adjIndex, index);
                        }
                    }
                }
            }
        }

        int ans = 0;

        for (int score : uf.scores) {
            ans = Math.max(ans, score);
        }

        return ans;
    }

    class UnionFind {
        int[] parents;
        int[] ranks;
        int[] scores;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            scores = new int[n];

            for (int i = 0; i < n; i++) parents[i] = i;
        }

        public int find(int i) {
            if (parents[i] != i) {
                parents[i] = find(parents[i]);
            }

            return parents[i];
        }

        public void merge(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) return;

            if (ranks[a] > ranks[b]) {
                parents[b] = a;
                scores[a] += scores[b];
            } else if (ranks[a] < ranks[b]) {
                parents[a] = b;
                scores[b] += scores[a];
            } else {
                parents[b] = a;
                scores[a] += scores[b];
                ranks[a]++;
            }
        }
    }
}