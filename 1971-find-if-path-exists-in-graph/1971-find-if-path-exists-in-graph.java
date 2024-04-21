class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        return mySol_disjoint(n, edges, source, destination);
    }

    public boolean mySol_graph(int n, int[][] edges, int source, int dest) {
        Map<Integer, List<Integer>> graph = new HashMap();

        for (int [] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }

        return dfs(source, dest, graph, new boolean[n]);
    }

    private boolean bfs(int source, int dest, Map<Integer, List<Integer>> graph, boolean[] visit) {
        Queue<Integer> queue = new LinkedList();
        queue.add(source);
        visit[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == dest) return true;

            if (!graph.containsKey(node)) return false;

            for (int next : graph.get(node)) {
                if (!visit[next]) {
                    queue.add(next);
                    visit[next] = true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int node, int dest, Map<Integer, List<Integer>> graph, boolean[] visit) {
        if (node == dest) return true;

        if (visit[node] || !graph.containsKey(node)) return false;

        visit[node] = true;

        for (int next : graph.get(node)) {
            if (!visit[next] && dfs(next, dest, graph, visit)) return true;
        }

        return false;
    }

    public boolean mySol_disjoint(int n, int[][] edges, int source, int dest) {
        UnionFind uf = new UnionFind(n);

        for (int [] edge : edges) {
            uf.merge(edge[0], edge[1]);
        }

        return uf.find(source) == uf.find(dest);
    }

    private class UnionFind {
        int[] parents;

        UnionFind(int n) {
            parents = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        int find(int i) {
            if (parents[i] != i) {
                parents[i] = find(parents[i]);
            }

            return parents[i];
        }

        void merge(int a, int b) {
            a = find(a);
            b = find(b);

            if (a > b) {
                a = a + b;
                b = a - b;
                a = a - b;
            }

            parents[b] = a;
        }
    }
}