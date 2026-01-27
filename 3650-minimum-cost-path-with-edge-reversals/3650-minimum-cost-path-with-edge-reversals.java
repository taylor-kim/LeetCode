class Solution {
    public int minCost(int n, int[][] edges) {
        return mySol2(n, edges);
    }

    public int mySol2(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap();

        int[][] costs = new int[n][n];

        for (int[] row : costs) {
            Arrays.fill(row, max);
        }

        for (int i = 0; i < n; i++) {
            costs[i][i] = 0;
        }

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new HashSet()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new HashSet()).add(edge[0]);

            costs[edge[0]][edge[1]] = Math.min(costs[edge[0]][edge[1]], edge[2]);
            costs[edge[1]][edge[0]] = Math.min(costs[edge[1]][edge[0]], 2 * edge[2]);
        }

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        pq.add(new int[] {0, 0});

        while (!pq.isEmpty()) {
            int cost = pq.peek()[0];
            int node = pq.poll()[1];

            if (node == n - 1) {
                return costs[0][node];
            }

            if (!graph.containsKey(node)) continue;

            for (int next : graph.get(node)) {
                if (costs[0][next] >= costs[0][node] + costs[node][next]) {
                    costs[0][next] = costs[0][node] + costs[node][next];

                    pq.add(new int[] {cost + costs[node][next], next});
                }
            }
        }

        return -1;
    }

    int max = (int)1e9 + 7;

    public int mySol_tle(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap();

        int[][] costs = new int[n][n];

        for (int[] row : costs) {
            Arrays.fill(row, max);
        }

        for (int i = 0; i < n; i++) {
            costs[i][i] = 0;
        }

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new HashSet()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new HashSet()).add(edge[0]);

            costs[edge[0]][edge[1]] = Math.min(costs[edge[0]][edge[1]], edge[2]);
            costs[edge[1]][edge[0]] = Math.min(costs[edge[1]][edge[0]], 2 * edge[2]);
        }

        topdown(0, n, graph, costs);

        return costs[0][n - 1] == max ? - 1 : costs[0][n - 1];
    }

    private void topdown(int node, int n, Map<Integer, Set<Integer>> graph, int[][] costs) {
        if (node == n - 1 || !graph.containsKey(node)) return;

        for (int next : graph.get(node)) {
            // System.out.println("costs[0][%d]:%d + costs[%d][%d]:%d < costs[0][%d]:%d"
            // .formatted(node, costs[0][node], node, next, costs[node][next], next, costs[0][next]));
            if (costs[0][node] + costs[node][next] <= costs[0][next]) {
                // System.out.println("wtf! node:%d, next:%d, costs:%d".formatted(node, next, costs[node][next]));
                costs[0][next] = costs[0][node] + costs[node][next];
                topdown(next, n, graph, costs);
            }
        }
    }
}