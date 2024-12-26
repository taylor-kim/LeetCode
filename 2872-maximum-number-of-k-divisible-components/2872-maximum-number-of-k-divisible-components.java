class Solution {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        return editorial_bfs(n, edges, values, k);
    }

    public int editorial_bfs(int n, int[][] edges, int[] values, int k) {
        if (n < 2) return 1;

        Map<Integer, Set<Integer>> graph = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], key -> new HashSet()).add(edge[1]);
            graph.computeIfAbsent(edge[1], key -> new HashSet()).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList();

        for (int key : graph.keySet()) {
            if (graph.get(key).size() == 1) {
                queue.add(key);
            }
        }

        long[] longValues = new long[n];

        for (int i = 0; i < values.length; i++) {
            longValues[i] = values[i];
        }

        int ans = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            int next = -1;

            if (graph.containsKey(node) && graph.get(node).size() > 0) {
                next = graph.get(node).iterator().next();
            }

            if (next >= 0) {
                remove(graph, node, next);
                remove(graph, next, node);
            }

            if (longValues[node] % k == 0) {
                ans++;
            } else if (next >= 0) {
                longValues[next] += longValues[node];
            }

            if (next >= 0 && graph.containsKey(next) && graph.get(next).size() == 1) {
                queue.add(next);
            }
        }

        return ans;
    }

    public int editorial_dfs(int n, int[][] edges, int[] values, int k) {
        Map<Integer, List<Integer>> graph = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], key -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], key -> new ArrayList()).add(edge[0]);
        }

        int[] ans = new int[1];

        dfs(graph, -1, 0, values, k, ans);

        return ans[0];
    }

    private int dfs(Map<Integer, List<Integer>> graph, int parent, int node, int[] values, int k, int[] ans) {
        int sum = 0;

        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            if (next != parent) {
                sum += dfs(graph, node, next, values, k, ans);
            }
        }

        sum += values[node];
        sum %= k;

        if (sum == 0) {
            ans[0]++;
        }

        return sum;
    }

    public int tryAgain_20241226(int n, int[][] edges, int[] values, int k) {
        Map<Integer, Set<Integer>> graph = new HashMap();
        Map<Integer, Set<Integer>> graph2 = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], key -> new HashSet()).add(edge[1]);
            graph.computeIfAbsent(edge[1], key -> new HashSet()).add(edge[0]);

            graph2.computeIfAbsent(edge[0], key -> new HashSet()).add(edge[1]);
            graph2.computeIfAbsent(edge[1], key -> new HashSet()).add(edge[0]);
        }

        int root = -1;

        for (int key : graph.keySet()) {
            if (graph.get(key).size() == 2) {
                root = key;
                break;
            }
        }

        UnionFind uf = new UnionFind(n);

        while (graph2.containsKey(root)) {
            // dfs()
            dfs(graph, graph2, root, root, k, new HashSet(), values, uf);
        }

        return uf.getComponentsCount();
    }

    private void dfs(Map<Integer, Set<Integer>> graph, Map<Integer, Set<Integer>> graph2,
            int parent, int node, int k,
            Set<Integer> visit, int[] values, UnionFind uf) {

        if (!visit.add(node)) {
            return;
        }

        // System.out.println(String.format("parent:%d, node:%d", parent, node));

        if (parent != node && isLeaf(graph2, node)) {
            if (values[node] % k == 0) {

            } else {
                values[parent] += values[node];
                uf.merge(parent, node);
                // System.out.println(String.format("merged, p:%d <= n:%d", parent, node));
            }
            
            remove(graph2, parent, node);
            remove(graph2, node, parent);

            // System.out.println(String.format("node is leaf. node:%d, value:%d, g2:%s", node, values[node], graph2));
        } else {
            for (int next : graph.get(node)) {
                dfs(graph, graph2, node, next, k, visit, values, uf);
            }
        }
    }

    private boolean isLeaf(Map<Integer, Set<Integer>> graph, int node) {
        return graph.get(node) == null || graph.get(node).size() == 1;
    }

    private void remove(Map<Integer, Set<Integer>> graph, int a, int b) {
        if (!graph.containsKey(a)) return;

        graph.get(a).remove(b);

        if (graph.get(a).size() == 0) {
            graph.remove(a);
        }
    }

    public class UnionFind {
        private int[] parents;

        public UnionFind(int n) {
            parents = new int[n];

            for (int i = 0; i < n; i++) parents[i] = i;
        }

        public int find(int node) {
            if (parents[node] != node) {
                parents[node] = find(parents[node]);
            }

            return parents[node];
        }

        public void merge(int a, int b) {
            parents[b] = a;
        }

        public int getComponentsCount() {
            Set<Integer> set = new HashSet();

            for (int i = 0; i < parents.length; i++) {
                set.add(find(i));
            }

            return set.size();
        }
    }

    public int mySol_fail(int n, int[][] edges, int[] values, int k) {
        Map<Integer, List<Integer>> graph = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], key -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], key -> new ArrayList()).add(edge[0]);
        }

        return dfs(graph, 0, 0, n, values, k, new HashSet());
    }

    private int dfs(Map<Integer, List<Integer>> graph, int node, int sum, int n, int[] values, int k, Set<String> used) {
        if (node >= n) {
            System.out.println(String.format("node:%d, sum:%d, k:%d", node, sum, k));
            return (sum != 0 && sum % k == 0) ? 1 : 0;
        }

        System.out.println(String.format("node:%d, sum:%d, k:%d", node, sum, k));

        int ans = 0;

        if (values[node] % k == 0 || (sum + values[node]) % k == 0) {
            for (int next : graph.get(node)) {
                String key = Math.min(node, next) + "_" + Math.max(node, next);
                if (used.add(key)) {
                    ans = Math.max(ans, 1 + dfs(graph, next, 0, n, values, k, used));
                    used.remove(key);
                }
            }
        }

        for (int next : graph.get(node)) {
            String key = Math.min(node, next) + "_" + Math.max(node, next);
            if (used.add(key)) {
                ans = Math.max(ans, dfs(graph, next, sum + values[node], n, values, k, used));
                used.remove(key);
            }
        }

        return ans;
    }
}