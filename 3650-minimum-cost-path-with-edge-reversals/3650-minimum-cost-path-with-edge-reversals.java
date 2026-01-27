class Solution {
    int max = (int)1e9 + 7;

    public int minCost(int n, int[][] edges) {
        return mySol3(n, edges);
    }

    public int mySol3(int n, int[][] edges) {
        Map<Integer, List<int[]>> graph = new HashMap();

        int[] minCosts = new int[n];
        Arrays.fill(minCosts, max);
        minCosts[0] = 0;

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(new int[] {edge[1], edge[2]});
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(new int[] {edge[0], 2 * edge[2]});
        }

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        pq.add(new int[] {0, 0});

        while (!pq.isEmpty()) {
            int cost = pq.peek()[0];
            int node = pq.poll()[1];

            if (node == n - 1) {
                return cost;
            }

            if (!graph.containsKey(node)) continue;

            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int nextCost = next[1];
                int newCost = cost + nextCost;

                if (minCosts[nextNode] > newCost) {
                    minCosts[nextNode] = newCost;

                    pq.add(new int[] {newCost, nextNode});
                }
            }
        }

        return -1;
    }

    public int mySol2(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap();
        Map<Integer, Map<Integer, Integer>> costs = new HashMap();

        int[] minCosts = new int[n];
        Arrays.fill(minCosts, max);
        minCosts[0] = 0;

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new HashSet()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new HashSet()).add(edge[0]);

            Map<Integer, Integer> oneCost = costs.computeIfAbsent(edge[0], k -> new HashMap());
            oneCost.put(edge[1], Math.min(oneCost.getOrDefault(edge[1], max), edge[2]));

            Map<Integer, Integer> twoCost = costs.computeIfAbsent(edge[1], k -> new HashMap());
            twoCost.put(edge[0], Math.min(twoCost.getOrDefault(edge[0], max), 2 * edge[2]));
        }

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        pq.add(new int[] {0, 0});

        while (!pq.isEmpty()) {
            int cost = pq.peek()[0];
            int node = pq.poll()[1];

            if (node == n - 1) {
                return cost;
            }

            if (!graph.containsKey(node)) continue;

            for (int next : graph.get(node)) {
                int newCost = cost + costs.get(node).get(next);

                if (minCosts[next] >= newCost) {
                    minCosts[next] = newCost;

                    pq.add(new int[] {newCost, next});
                }
            }
        }

        return -1;
    }

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