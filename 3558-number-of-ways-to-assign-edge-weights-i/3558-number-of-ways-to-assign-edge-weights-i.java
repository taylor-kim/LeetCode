class Solution {
    public int assignEdgeWeights(int[][] edges) {
        return mySol(edges);
    }

    public int mySol(int[][] edges) {
        Map<Integer, List<Integer>> graph = getGraph(edges);

        int max = getMax(graph, 1, 0);

        return topdown2(max);
    }

    private int topdown2(int depth) {
        if (depth == 1) return 1;

        int ret = topdown2(depth - 1);

        int mod = (int)1e9 + 7;

        return (ret + ret) % mod;
    }

    private int[] topdown(int depth) {
        if (depth == 1) return new int[] {1, 1};

        int[] oddEven = topdown(depth - 1);

        int mod = (int)1e9 + 7;

        int odd = (oddEven[0] + oddEven[1]) % mod;
        int even = (oddEven[0] + oddEven[1]) % mod;

        return new int[] {odd, even};
    }

    private int getMax(Map<Integer, List<Integer>> graph, int node, int prev) {
        int max = 0;

        for (int next : graph.get(node)) {
            if (next == prev) continue;

            max = Math.max(max, 1 + getMax(graph, next, node));
        }

        return max;
    }

    private Map<Integer, List<Integer>> getGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }

        return graph;
    }
}