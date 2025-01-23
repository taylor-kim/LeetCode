class Solution {
    public int countServers(int[][] grid) {
        return official_server_grouping(grid);
    }

    public int try_disjointset_fail(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        UnionFind uf = new UnionFind(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;

                if (grid[i][j] == 1) {
                    uf.merge(i, index);
                    uf.merge(j, index);
                }
            }
        }

        return uf.getComponentCount();
    }

    public class UnionFind {
        int[] parents;
        // int componentCount;

        public UnionFind(int n) {
            parents = new int[n];
            // componentCount = n;

            for (int i = 0; i < n; i++) parents[i] = i;
        }

        public int find(int n) {
            if (parents[n] != n) {
                parents[n] = find(parents[n]);
            }

            return parents[n];
        }

        public void merge(int p, int c) {
            p = find(p);
            c = find(c);

            if (p != c) {
                System.out.println(String.format("merge : %d <= %d", p, c));
                parents[c] = p;
                // componentCount--;
            }
        }

        public int getComponentCount() {
            Map<Integer, Integer> map = new HashMap();

            System.out.println(Arrays.toString(parents));

            for (int i = 0; i < parents.length; i++) {
                map.put(find(i), map.getOrDefault(find(i), 0) + 1);
            }

            System.out.println(map);

            int componentCount = 0;

            for (int count : map.values()) {
                if (count > 1) componentCount++;
            }

            return componentCount;
        }
    }

    public int official_server_grouping(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] serverCountInCol = new int[n];
        int[] lastColInRow = new int[m];
        Arrays.fill(lastColInRow, -1);

        int ans = 0;

        for (int i = 0; i < m; i++) {
            int serverCountInRow = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    serverCountInRow++;
                    serverCountInCol[j]++;
                    lastColInRow[i] = j;
                }
            }

            if (serverCountInRow > 1) {
                ans += serverCountInRow;
                lastColInRow[i] = -1;
            }
        }

        for (int i = 0; i < m; i++) {
            if (lastColInRow[i] != -1 && serverCountInCol[lastColInRow[i]] > 1) {
                ans++;
            }
        }

        return ans;
    }

    public int official_track_with_two_arrays(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] numberOfRow = new int[m];
        int[] numberOfCol = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    numberOfRow[i]++;
                    numberOfCol[j]++;
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1
                    && (numberOfRow[i] > 1 || numberOfCol[j] > 1)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    public int mySol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] numberOfRow = new int[m];
        int[] numberOfCol = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    numberOfRow[i]++;
                    numberOfCol[j]++;
                }
            }
        }

        Set<Integer> set = new HashSet();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1
                    && (numberOfRow[i] > 1 || numberOfCol[j] > 1)) {
                    set.add(i * n + j);
                }
            }
        }

        return set.size();
    }
}