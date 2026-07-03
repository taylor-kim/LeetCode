class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        return mySol_after_topic(edges, online, k);
    }

    public int mySol_after_topic(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        Queue<long[]> pq = new PriorityQueue<>((a, b) -> {
            return b[2] != a[2] ? Long.compare(b[2], a[2]) : Long.compare(a[1], b[1]);
        });

        Map<Integer, List<int[]>> graph = new HashMap();

        for (int[] edge : edges) {
            if (online[edge[0]] && online[edge[1]]) {
                graph.computeIfAbsent(edge[0], key -> new ArrayList()).add(new int[] {edge[1], edge[2]});
            }
        }

        for (List<int[]> list : graph.values()) {
            Collections.sort(list, (a, b) -> {
                return a[1] - b[1];
            });
        }

        int lo = 0;
        int hi = Integer.MAX_VALUE;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (find(graph, k, mid, n)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean find(Map<Integer, List<int[]>> graph, long k, int min, int n) {
        Queue<long[]> pq = new PriorityQueue<>((a, b) -> {
            // return b[2] != a[2] ? Long.compare(b[2], a[2]) : Long.compare(a[1], b[1]);
            return Long.compare(a[1], b[1]);
        });

        pq.add(new long[] {0, 0});
        long[] minCost = new long[n];
        Arrays.fill(minCost, Long.MAX_VALUE);
        minCost[0] = 0;

        while (!pq.isEmpty()) {
            long[] data = pq.poll();
            int node = (int)data[0];
            long totalCost = data[1];

            // System.out.println("n:%d, node:%d, totalCost:%d, minCost:%d, min:%d".formatted(n, node, totalCost, minCost, min));

            if (node == n - 1) {
                // System.out.println("n:%d, node:%d, totalCost:%d, minCost:%d, min:%d".formatted(n, node, totalCost, minCost, min));
            }

            if (node == n - 1 && totalCost <= k) {
                // System.out.println("node:%d, totalCost:%d, minCost:%d, min:%d".formatted(node, totalCost, minCost, min));
                return true;
            }

            if (totalCost > k) continue;

            if (!graph.containsKey(node)) continue;
            
            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int addingCost = next[1];
                long nextTotalCost = totalCost + addingCost;

                if (nextTotalCost > k || addingCost < min) continue;

                if (minCost[nextNode] > nextTotalCost) {
                    minCost[nextNode] = nextTotalCost;
                    pq.add(new long[] {nextNode, nextTotalCost});
                }
            }
        }

        return false;
    }

    public int mySol_fail_mle(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        Queue<long[]> pq = new PriorityQueue<>((a, b) -> {
            return b[2] != a[2] ? Long.compare(b[2], a[2]) : Long.compare(a[1], b[1]);
        });

        Map<Integer, List<int[]>> graph = new HashMap();
        Map<Integer, Map<Integer, Integer>> costs = new HashMap();

        for (int[] edge : edges) {
            if (online[edge[0]] && online[edge[1]]) {
                graph.computeIfAbsent(edge[0], key -> new ArrayList()).add(new int[] {edge[1], edge[2]});
            }
        }

        pq.add(new long[] {0, 0, Integer.MAX_VALUE});

        while (!pq.isEmpty()) {
            long[] data = pq.poll();
            int node = (int)data[0];
            long totalCost = data[1];
            int minCost = (int)data[2];

            if (node == n - 1 && totalCost <= k) {
                return minCost;
            }

            if (totalCost > k) continue;

            if (!graph.containsKey(node)) continue;
            
            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                if (!online[nextNode]) continue;
                int addingCost = next[1];

                if (totalCost + addingCost > k) continue;

                pq.add(new long[] {nextNode, totalCost + addingCost, Math.min(minCost, addingCost)});
            }
        }

        return -1;
    }
}