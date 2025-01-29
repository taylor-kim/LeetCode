class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        return official_bf(edges);
    }

    public int[] official_bf(int[][] edges) {
        int n = edges.length;

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge : edges) {
            if (isConnected(graph, edge[0] - 1, edge[1] - 1, new boolean[n])) {
                return edge;
            }
            graph[edge[0] - 1].add(edge[1] - 1);
            graph[edge[1] - 1].add(edge[0] - 1);
        }

        return null;
    }

    private boolean isConnected(List<Integer>[] graph, int node, int target, boolean[] visit) {
        if (node == target) return true;

        visit[node] = true;

        for (int next : graph[node]) {
            if (!visit[next] && isConnected(graph, next, target, visit)) {
                return true;
            }
        }

        return false;
    }

    public int[] official_uf(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (!uf.merge(edge[0], edge[1])) {
                return edge;
            }
        }

        return null;
    }

    public int[] mySol2_prim(int[][] edges) {
        int n = edges.length;
        for (int i = n - 1; i >= 0; i--) {
            UnionFind uf = new UnionFind(n);

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                uf.merge(edges[j][0], edges[j][1]);
            }

            if (uf.isAllConnected()) {
                return edges[i];
            }
        }

        return null;
    }

    class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n + 1];
            ranks = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parents[i] = i;
            }
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }

        public boolean merge(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) {
                return false;
            } else {
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

        public boolean isAllConnected() {
            Set<Integer> parentsSet = new HashSet();

            for (int i = 1; i < parents.length; i++) {
                parentsSet.add(find(i));
            }

            return parentsSet.size() == 1;
        }
    }

    public int[] mySol_dfs_hold(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new HashSet()).add(edge[1]);
        }

        for (int i = edges.length - 1; i >= 0; i--) {
            if (isConnected(graph, 1, edges[i], new boolean[edges.length + 1])) {
                return edges[i];
            }
        }

        return null;
    }

    private boolean isConnected(Map<Integer, Set<Integer>> graph, int node, int[] redundant, boolean[] visit) {
        if (visit[node]) return true;

        visit[node] = true;

        for (int next : graph.getOrDefault(node, new HashSet<>())) {
            
        }

        visit[node] = false;

        return true;
    }
}