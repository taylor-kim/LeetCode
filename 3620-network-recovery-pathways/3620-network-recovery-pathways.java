class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        return try_20260711(edges, online, k);
    }

    public int try_20260711(int[][] edges, boolean[] online, long k) {
        Map<Integer, List<int[]>> graph = new HashMap();
        int max = 0;

        for (int[] edge : edges) {
            if (online[edge[0]] && online[edge[1]]) {
                graph.computeIfAbsent(edge[0], key -> new ArrayList()).add(new int[] {edge[1], edge[2]});

                max = Math.max(max, edge[2]);
            }
        }

        for (List<int[]> list : graph.values()) {
            Collections.sort(list, (a, b) -> {
                return a[1] - b[1];
            });
        }

        int lo = 0;
        int hi = max + 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (find(graph, online.length, k, mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }

    private boolean find(Map<Integer, List<int[]>> graph, int n, long k, int min) {
        long[] costs = new long[n];

        Arrays.fill(costs, Long.MAX_VALUE);
        costs[0] = 0;

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> {
            return Long.compare(costs[a], costs[b]);
        });
        queue.add(0);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == n - 1 && costs[node] <= k) {
                return true;
            }

            if (!graph.containsKey(node)) continue;

            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                long nextCost = costs[node] + next[1];

                if (costs[nextNode] > nextCost && k >= nextCost && next[1] >= min) {
                    costs[nextNode] = nextCost;
                    queue.add(nextNode);
                }
            }
        }

        return false;
    }
}