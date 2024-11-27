class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        return mySol(n, queries);
    }

    public int[] mySol(int n, int[][] queries) {
        List<Integer>[] edges = new List[n];

        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList();
            
            if (i > 0) {
                edges[i - 1].add(i);
            }
        }

        // System.out.println(Arrays.toString(edges));

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            edges[query[0]].add(query[1]);

            ans[i] = getShortestPath(edges, n);
        }

        return ans;
    }

    private int getShortestPath(List<Integer>[] edges, int n) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        pq.add(new int[] {0, 0});

        Set<Integer> visit = new HashSet();
        visit.add(0);

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int distance = data[0];
            int node = data[1];

            if (node == n - 1) {
                return distance;
            }

            for (int next : edges[node]) {
                if (visit.add(next)) {
                    pq.add(new int[] {distance + 1, next});
                }
            }
        }

        return n;
    }
}