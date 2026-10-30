class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        return mySol(n, edges, labels, k);
    }

    public int mySol(int n, int[][] edges, String labels, int k) {
        Map<Integer, List<int[]>> graph = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], key -> new ArrayList()).add(new int[] {edge[1], edge[2]});
        }

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        pq.add(new int[] {0, 0, labels.charAt(0) - 'a', 1});

        int[][] costs = new int[n][k + 1];

        for (int[] arr : costs) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        costs[0][1] = 0;

        // Arrays.fill(costs, Integer.MAX_VALUE);
        // costs[0] = 0;

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int node = data[0];
            int w = data[1];
            int num = data[2];
            int count = data[3];

            // if (count > k) continue;

            if (node == n - 1) return w;

            if (!graph.containsKey(node)) continue;

            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int nextW = w + next[1];

                // if (costs[nextNode][] <= nextW) continue;

                // costs[nextNode] = nextW;

                int nextNum = labels.charAt(nextNode) - 'a';
                int nextCount = 1;
                
                if (nextNum == num) {
                    nextCount = count + 1;
                }

                if (nextCount > k) continue;

                if (costs[nextNode][nextCount] <= nextW) continue;

                costs[nextNode][nextCount] = nextW;

                pq.add(new int[] {nextNode, nextW, nextNum, nextCount});
            }
        }

        return -1;
    }
}