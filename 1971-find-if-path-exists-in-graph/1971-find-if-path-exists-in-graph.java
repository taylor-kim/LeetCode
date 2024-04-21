class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        return mySol(n, edges, source, destination);
    }

    public boolean mySol(int n, int[][] edges, int source, int dest) {
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
}