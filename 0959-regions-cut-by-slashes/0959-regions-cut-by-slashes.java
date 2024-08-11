class Solution {
    public int regionsBySlashes(String[] grid) {
        return official_disjoinset_graph(grid);
    }

    public int official_disjoinset_graph(String[] grid) {
        int n = grid.length;
        int n2 = n + 1;
        
        UnionFindGraph ufg = new UnionFindGraph(n2 * n2);

        for (int i = 0; i < n2; i++) {
            // for (int j = 0; j < n2; j++) {
            //     if (i == 0 || j == 0 || i == n2 - 1 || j == n2 - 1) {
            //         ufg.parents[i * n2 + j] = 0;
            //     }
            // }
            int top = i;
            int bot = (n2 - 1) * n2 + i;
            int left = i * n2;
            int right = i * n2 + n2 - 1;

            ufg.parents[top] = 0;
            ufg.parents[bot] = 0;
            ufg.parents[left] = 0;
            ufg.parents[right] = 0;
        }

        ufg.parents[0] = -1;
        int regionCount = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == '/') {
                    int topRight = i * n2 + j + 1;
                    int bottomLeft = (i + 1) * n2 + j;
                    regionCount += ufg.union(topRight, bottomLeft);
                } else if (grid[i].charAt(j) == '\\') {
                    int topLeft = i * n2 + j;
                    int bottomRight = (i + 1) * n2 + j + 1;
                    regionCount += ufg.union(topLeft, bottomRight);
                }
            }
        }

        return regionCount;
    }

    public class UnionFindGraph {
        int[] parents;

        UnionFindGraph(int n) {
            parents = new int[n];
            Arrays.fill(parents, -1);
        }

        int union(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) {
                return 1;
            } else {
                parents[a] = b;

                return 0;
            }
        }

        int find(int a) {
            if (parents[a] == -1) {
                return a;
            }

            return parents[a] = find(parents[a]);
        }
    }

    public int official_disjoinset_triangle(String[] grid) {
        int n = grid.length;
        int totalTriangles = n * n * 4;
        int[] parents = new int[totalTriangles];

        for (int i = 0; i < parents.length; i++) parents[i] = i;

        int regionCount = totalTriangles;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    regionCount -= union(parents, getIndex(n, i - 1, j, 2), getIndex(n, i, j, 0));
                }
                if (j > 0) {
                    regionCount -= union(parents, getIndex(n, i, j - 1, 1), getIndex(n, i, j, 3));
                }
                if (grid[i].charAt(j) != '/') {
                    regionCount -= union(parents, getIndex(n, i, j, 0), getIndex(n, i, j, 1));
                    regionCount -= union(parents, getIndex(n, i, j, 2), getIndex(n, i, j, 3));
                }
                if (grid[i].charAt(j) != '\\') {
                    regionCount -= union(parents, getIndex(n, i, j, 0), getIndex(n, i, j, 3));
                    regionCount -= union(parents, getIndex(n, i, j, 1), getIndex(n, i, j, 2));
                }
            }
        }

        return regionCount;
    }

    private int getIndex(int n, int row, int col, int triNum) {
        return (row * n * 4) + (col * 4) + triNum;
    }

    private int union(int[] parents, int a, int b) {
        a = find(parents, a);
        b = find(parents, b);

        if (a != b) {
            parents[b] = a;

            return 1;
        } else {
            return 0;
        }
    }

    private int find(int[] parents, int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = find(parents, parents[a]);
    }

    public int official_expanded_grid(String[] grid) {
        int n = grid.length;
        int multi = 3;
        int[][] expanded = new int[n * multi][n * multi];

        for (int i = 0; i < n; i++) {
            String line = grid[i];

            for (int j = 0; j < n; j++) {
                char c = line.charAt(j);

                if (c == '/') {
                    for (int k = 0; k < multi; k++) {
                        expanded[i * multi + k][j * multi + (multi - k - 1)] = 1;
                    }
                } else if (c == '\\') {
                    for (int k = 0; k < multi; k++) {
                        expanded[i * multi + k][j * multi + k] = 1;
                    }
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < expanded.length; i++) {
            for (int j = 0; j < expanded[0].length; j++) {
                if (expanded[i][j] == 0) {
                    dfs(expanded, i, j);
                    ans++;
                }
            }
        }

        return ans;
    }

    private int[][] dirs = {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    private void dfs(int[][] grid, int y, int x) {
        if (y < 0 || x < 0 || y >= grid.length || x >= grid[0].length || grid[y][x] != 0) return;

        grid[y][x] = 2;

        for (int[] d : dirs) {
            dfs(grid, y + d[0], x + d[1]);
        }
    }

    Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> edges = new HashMap();

    public int mySol_fail(String[] grid) {
        int n = grid.length;

        Set<Pair<Integer, Integer>> coordinations = new HashSet();

        Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> edges = new HashMap();

        // Map<Pair<Integer, Integer>, Integer> indeg = new HashMap();

        for (int i = 0; i < n; i++) {
            Pair<Integer, Integer> currentTop = new Pair(0, i);
            Pair<Integer, Integer> nextTop = new Pair(0, i + 1);

            add(edges, currentTop, nextTop);

            Pair<Integer, Integer> currentBot = new Pair(n, i);
            Pair<Integer, Integer> nextBot = new Pair(n, i + 1);

            add(edges, currentBot, nextBot);

            Pair<Integer, Integer> currentLeft = new Pair(i, 0);
            Pair<Integer, Integer> nextLeft = new Pair(i + 1, 0);

            add(edges, currentLeft, nextLeft);

            Pair<Integer, Integer> currentRight = new Pair(i, n);
            Pair<Integer, Integer> nextRight = new Pair(i + 1, n);

            add(edges, currentRight, nextRight);
        }

        for (int i = 0; i < n; i++) {
            String line = grid[i];
            for (int j = 0; j < n; j++) {
                char c = line.charAt(j);

                if (c == '/') {
                    Pair<Integer, Integer> one = new Pair(i, j + 1);
                    Pair<Integer, Integer> two = new Pair(i + 1, j);

                    add(edges, one, two);
                } else if (c == '\\') {
                    Pair<Integer, Integer> one = new Pair(i, j);
                    Pair<Integer, Integer> two = new Pair(i + 1, j + 1);

                    add(edges, one, two);
                }
            }
        }

        Set<Set<Pair<Integer, Integer>>> set = new HashSet();
        Set<Pair<Integer, Integer>> visit = new HashSet();

        return 0;
    }

    private void bfs(Pair<Integer, Integer> start, Set<Set<Pair<Integer, Integer>>> result) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList();
        queue.add(start);
        
        Set<Pair<Integer, Integer>> visit = new HashSet();
        visit.add(start);

        while (queue.size() > 0) {
            Pair<Integer, Integer> current = queue.poll();

            if (!edges.containsKey(current)) continue;

            for (Pair<Integer, Integer> next : edges.get(current)) {
                
            }
        }
    }

    private void dfs(Pair<Integer, Integer> start, Pair<Integer, Integer> current
        , Set<Set<Pair<Integer, Integer>>> set, Set<Pair<Integer, Integer>> cand, Set<Pair<Integer, Integer>> visit) {
            if (visit.contains(current)) return;

            visit.add(current);

            if (!edges.containsKey(current)) return;

            for (Pair<Integer, Integer> next : edges.get(current)) {

            }
    }

    private void add(Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> edges
        , Pair<Integer, Integer> one, Pair<Integer, Integer> two) {
            edges.computeIfAbsent(one, k -> new ArrayList()).add(two);
            edges.computeIfAbsent(two, k -> new ArrayList()).add(one);
    }
}