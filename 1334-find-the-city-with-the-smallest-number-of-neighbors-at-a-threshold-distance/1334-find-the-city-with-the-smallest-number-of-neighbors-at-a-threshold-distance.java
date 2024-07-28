class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        return mySol_pq(n, edges, distanceThreshold);
    }

    public int mySol_pq(int n, int[][] edges, int distanceThreshold) {
        Map<Integer, List<int[]>> graph = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(new int[] {edge[1], edge[2]});
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(new int[] {edge[0], edge[2]});
        }

        int minCount = Integer.MAX_VALUE;
        int ans = -1;

        for (int node = 0; node < n; node++) {
            if (!graph.containsKey(node)) {
                minCount = 0;
                ans = node;
            }

            int count = countOfNeighbors(node, graph, distanceThreshold, n);

            // System.out.println(String.format("city:%d, count:%d", node, count));

            if (minCount >= count) {
                minCount = count;
                ans = node;
            }
        }

        return ans;
    }

    private int countOfNeighbors(int start, Map<Integer, List<int[]>> graph, int limit, int n) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        int[] shortestDist = new int[n];

        for (int i = 0; i < n; i++) {
            shortestDist[i] = Integer.MAX_VALUE;
        }

        // if (graph.containsKey(start)) {
        //     for (int[] next : graph.get(start)) {
        //         queue.add(new int[] {next[0], next[1]});
        //     }
        // }
        shortestDist[start] = 0;

        queue.add(new int[] {start, 0});

        Set<Integer> counter = new HashSet();

        int minDistance = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int node = queue.peek()[0];
            int dist = queue.poll()[1];

            // if (shortestDist[node] < dist) continue;

            // shortestDist[node] = dist;

            // System.out.println(String.format("start:%d, current:%d, dist:%d", start, node, dist));

            if (graph.containsKey(node)) {
                for (int[] next : graph.get(node)) {
                    int nextNode = next[0];
                    int nextDist = next[1] + dist;

                    if (shortestDist[nextNode] > nextDist && nextDist <= limit) {
                        shortestDist[nextNode] = nextDist;
                        queue.add(new int[] {nextNode, nextDist});

                        counter.add(nextNode);
                    }
                }
            }
        }

        return counter.size();
    }
}