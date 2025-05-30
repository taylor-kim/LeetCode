class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        return mySol(edges, node1, node2);
    }

    public int mySol(int[] edges, int node1, int node2) {
        Map<Integer, List<Integer>> graph = new HashMap();

        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != -1) {
                graph.computeIfAbsent(i, k -> new ArrayList<>()).add(edges[i]);
            }
        }

        // System.out.println(graph);

        Map<Integer, List<Integer>> distances = new HashMap();
        boolean[] visit = new boolean[edges.length + 1];

        dfs(graph, node1, 0, distances, visit);

        // System.out.println(distances);

        dfs(graph, node2, 0, distances, visit);

        // System.out.println(distances);

        int min = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        for (Integer node : distances.keySet()) {
            List<Integer> dist = distances.get(node);

            if (dist.size() == 2) {
                int max = Math.max(dist.get(0), dist.get(1));

                // System.out.println(String.format("min:%d, max:%d, node:%d, ans:%d", min, max, node, ans));

                if (min > max) {
                    min = max;
                    ans = node;
                } else if (min == max) {
                    ans = Math.min(ans, node);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(Map<Integer, List<Integer>> graph, int node, int dist, Map<Integer, List<Integer>> distances, boolean[] visit) {
        if (visit[node]) return;

        visit[node] = true;

        List<Integer> list = graph.getOrDefault(node, new ArrayList<>());

        distances.computeIfAbsent(node, k -> new ArrayList<>()).add(dist);

        for (int next : list) {
            dfs(graph, next, dist + 1, distances, visit);
        }

        visit[node] = false;
    }
}