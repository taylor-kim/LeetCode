class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        return mySol(edges1, edges2, k);
    }

    public int[] mySol(int[][] edges1, int[][] edges2, int k) {
        Map<Integer, List<Integer>> g1 = getGraph(edges1);
        Map<Integer, List<Integer>> g2 = getGraph(edges2);

        int maxNodeForG2 = maxNode(g2);

        int[] ans = new int[edges1.length + 1];
        boolean[] visit = new boolean[Math.max(edges1.length, edges2.length) + 1];

        int sub = 0;

        if (k > 0) {
            for (int j = 0; j < edges2.length + 1; j++) {
                sub = Math.max(sub, getNodeCount(g2, j, k - 1, visit));
            }
        }

        for (int i = 0; i < ans.length; i++) {
            ans[i] = getNodeCount(g1, i, k, visit);

            ans[i] += sub;
        }

        return ans;
    }

    private Map<Integer, List<Integer>> getGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }

        return graph;
    }

    private int maxNode(Map<Integer, List<Integer>> graph) {
        int count = 0;
        int maxNode = -1;

        for (int node : graph.keySet()) {
            if (graph.get(node).size() > count) {
                maxNode = node;
                count = graph.get(node).size();
            }
        }

        return maxNode;
    }

    private int getNodeCount(Map<Integer, List<Integer>> graph, int node, int k, boolean[] visit) {
        if (visit[node]) return 0;

        if (k == 0) return 1;

        int count = 1;

        visit[node] = true;

        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            count += getNodeCount(graph, next, k - 1, visit);
        }

        visit[node] = false;

        return count;
    }
}