class Solution {
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        return try_binary_lifting(edges, queries);
    }

    int m = 0;
    Map<Integer, List<Integer>> tree;
    int[] d;
    int[][] f;

    public int[] try_binary_lifting(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        for (int i = 31; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                m = i + 1;
                break;
            }
        }

        d = new int[n + 1];
        f = new int[n + 1][m + 1];

        tree = new HashMap();

        for (int[] edge : edges) {
            tree.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            tree.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }

        dfs(1, 0);

        for (int i = 1; i <= m; i++) {
            for (int node = 1; node <= n; node++) {
                f[node][i] = f[f[node][i - 1]][i - 1];
            }
        }

        int mod = (int)1e9 + 7;
        int[] pows = new int[n + 1];
        pows[0] = 1;
        for (int i = 1; i <= n; i++) {
            pows[i] = (int)((1l * pows[i - 1] * 2) % mod);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int p = queries[i][0];
            int q = queries[i][1];

            int lca = lca(p, q);

            int numberOfEdges = d[p] + d[q] - 2 * d[lca];

            // ans[i] = qpow(2, numberOfEdges - 1);
            if (numberOfEdges > 0) {
                ans[i] = pows[numberOfEdges - 1];
            }
        }

        return ans;
    }

    private void dfs(int node, int parent) {
        f[node][0] = parent;

        for (int next : tree.getOrDefault(node, new ArrayList<>())) {
            if (next == parent) continue;

            d[next] = d[node] + 1;

            dfs(next, node);
        }
    }

    private int lca(int x, int y) {
        if (d[x] > d[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        for (int i = m; i >= 0; i--) {
            if (d[x] <= d[f[y][i]]) {
                y = f[y][i];
            }
        }

        if (x == y) {
            return x;
        }

        for (int i = m; i >= 0; i--) {
            if (f[x][i] != f[y][i]) {
                x = f[x][i];
                y = f[y][i];
            }
        }

        return f[x][0];
    }

    Map<Integer, List<Integer>> graph = new HashMap();
    Map<Integer, Integer> parents = new HashMap();
    Map<Integer, Integer> depths = new HashMap();

    public int[] mySol(int[][] edges, int[][] queries) {
        int maxNode = buildGraph(edges);
        buildDirections(graph, 0, 1, 0);

        int n = queries.length;
        int[] ans = new int[n];
        Integer[][] memo = new Integer[maxNode + 1][maxNode + 1];

        for (int i = 0; i < n; i++) {
            int p = queries[i][0];
            int q = queries[i][1];

            int lca = findLCA(p, q, memo);

            int numberOfEdges = depths.get(p) - depths.get(lca) + depths.get(q) - depths.get(lca);

            ans[i] = qpow(2, numberOfEdges - 1);
        }

        return ans;
    }

    private int buildGraph(int[][] edges) {
        int max = 0;
        for (int[] edge : edges) {
            max = Math.max(max, Math.max(edge[0], edge[1]));
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }
        return max;
    }

    private void buildDirections(Map<Integer, List<Integer>> graph, int prev, int node, int depth) {
        // System.out.println("node:%d, prev:%d".formatted(node, prev));
        parents.put(node, prev);
        depths.put(node, depth);

        for (int next : graph.get(node)) {
            if (prev == next) continue;

            buildDirections(graph, node, next, depth + 1);
        }
    }

    private int findLCA(int p, int q, Integer[][] memo) {
        if (p == q) return p;

        if (memo[p][q] != null) return memo[p][q];

        int depthP = depths.get(p);
        int depthQ = depths.get(q);

        if (depthP > depthQ) {
            return memo[p][q] = findLCA(parents.get(p), q, memo);
        } else if (depthP < depthQ) {
            return memo[p][q] = findLCA(p, parents.get(q), memo);
        } else {
            return memo[p][q] = findLCA(parents.get(p), parents.get(q), memo);
        }
    }

    private int qpow(int x, int y) {
        if (y < 0) return 0;

        long res = 1;
        long base = x;
        int mod = (int)1e9 + 7;

        while (y > 0) {
            if ((y & 1) == 1) {
                res = (res * base) % mod;
            }

            base = (base * base) % mod;

            y >>= 1;
        }

        return (int)res;
    }
}