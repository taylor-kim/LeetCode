class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        return others_simple_bfs(n, queries);
    }

    public int[] others_simple_bfs(int n, int[][] queries) {
        List<Integer>[] edges = new List[n - 1];

        for (int i = 0; i < n - 1; i++) {
            edges[i] = new ArrayList();
            edges[i].add(i + 1);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            edges[q[0]].add(q[1]);

            Queue<Integer> queue = new LinkedList();
            Set<Integer> visit = new HashSet();

            queue.add(0);
            visit.add(0);

            int distance = 0;

            while (!queue.isEmpty()) {
                int size = queue.size();
                boolean found = false;

                while (size-- > 0) {
                    int node = queue.poll();

                    if (node == n - 1) {
                        found = true;
                        break;
                    }

                    for (int next : edges[node]) {
                        if (visit.add(next)) {
                            queue.add(next);
                        }
                    }
                }

                if (found) break;

                distance++;
            }

            ans[i] = distance;
        }

        return ans;
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