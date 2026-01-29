class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        return try_floyd_20260129(source, target, original, changed, cost);
    }

    public long try_floyd_20260129(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] costs = new long[26][26];

        long max = (long)1e7;

        for (long[] row : costs) {
            Arrays.fill(row, max);
        }

        for (int i = 0; i < costs.length; i++) {
            costs[i][i] = 0;
        }

        for (int i = 0; i < original.length; i++) {
            costs[original[i] - 'a'][changed[i] - 'a'] = Math.min(costs[original[i] - 'a'][changed[i] - 'a'], cost[i]);
        }

        for (int k = 0; k < costs.length; k++) {
            for (int i = 0; i < costs.length; i++) {
                for (int j = 0; j < costs.length; j++) {
                    if (i == j) continue;

                    costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < source.length(); i++) {
            int a = source.charAt(i) - 'a';
            int b = target.charAt(i) - 'a';

            long minCost = costs[a][b];

            if (minCost == max) return -1;

            ans += minCost;
        }

        return ans;
    }

    public long try_dijkstra_20260129(String source, String target, char[] original, char[] changed, int[] cost) {
        Map<Integer, List<long[]>> graph = new HashMap();

        for (int i = 0; i < original.length; i++) {
            int o = original[i] - 'a';
            int c = changed[i] - 'a';

            graph.computeIfAbsent(o, k -> new ArrayList()).add(new long[] {c, cost[i]});
        }

        long ans = 0;

        long[][] costs = new long[26][26];

        for (long[] row : costs) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < costs.length; i++) {
            costs[i][i] = 0;
        }

        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs.length; j++) {
                if (i == j) continue;

                long minCost = findMinCost(graph, i, j, costs.length);

                costs[i][j] = minCost;
            }
        }

        for (int i = 0; i < source.length(); i++) {
            int start = source.charAt(i) - 'a';
            int end = target.charAt(i) - 'a';

            long minCost = costs[start][end];

            if (minCost < 0) return -1;

            ans += minCost;
        }

        return ans;
    }

    private long findMinCost(Map<Integer, List<long[]>> graph, int start, int end, int n) {
        if (start == end) return 0;

        Queue<long[]> pq = new PriorityQueue<>((a, b) -> {
            return Long.compare(a[0], b[0]);
        });

        pq.add(new long[] {0, start});

        long[] minCost = new long[n];
        Arrays.fill(minCost, Long.MAX_VALUE);
        minCost[start] = 0;

        while (!pq.isEmpty()) {
            long cost = pq.peek()[0];
            int node = (int)pq.poll()[1];

            if (node == end) {
                return cost;
            }

            if (!graph.containsKey(node)) continue;

            for (long[] next : graph.get(node)) {
                int nextNode = (int)next[0];
                long nextCost = cost + next[1];

                if (minCost[nextNode] > nextCost) {
                    minCost[nextNode] = nextCost;

                    pq.add(new long[] {nextCost, nextNode});
                }
            }
        }

        return -1;
    }

    public long try_dijkstra(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] min = new long[26][26];

        long MAX = (long)1e6 * source.length();

        for (long[] a : min) {
            Arrays.fill(a, MAX);
        }

        Map<Integer, List<int[]>> graph = new HashMap();

        for (int i = 0; i < original.length; i++) {
            int from = original[i] - 'a';
            int to = changed[i] - 'a';

            graph.computeIfAbsent(from, k -> new ArrayList()).add(new int[] {to, cost[i]});
        }

        Queue<Pair<Integer, Long>> queue = new PriorityQueue<>((a, b) -> {
            return Long.compare(a.getValue(), b.getValue());
        });

        Set<Integer> visit = new HashSet();

        for (int i = 0; i < source.length(); i++) {
            int start = source.charAt(i) - 'a';

            min[start][start] = 0;

            queue.clear();
            queue.add(new Pair(start, 0L));

            while (!queue.isEmpty()) {
                Pair<Integer, Long> data = queue.poll();

                int node = data.getKey();
                long totalCost = data.getValue();

                // if (min[start][node] < totalCost) continue;

                // min[start][node] = Math.min(min[start][node], totalCost);

                if (!graph.containsKey(node)) {
                    continue;
                }

                for (int[] next : graph.get(node)) {
                    int nextNode = next[0];
                    long nextCost = next[1] + totalCost;

                    if (min[start][nextNode] > nextCost) {
                        min[start][nextNode] = nextCost;
                        queue.add(new Pair<>(nextNode, nextCost));
                    }
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < source.length(); i++) {
            long val = min[source.charAt(i) - 'a'][target.charAt(i) - 'a'];

            if (val == MAX) return -1;

            ans += val;
        }

        return ans;
    }

    public long mySol(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] min = new long[26][26];

        long MAX = (long)1e6 * source.length();

        for (long[] a : min) {
            Arrays.fill(a, MAX);
        }

        for (int i = 0; i < original.length; i++) {
            min[original[i] - 'a'][changed[i] - 'a'] = Math.min(min[original[i] - 'a'][changed[i] - 'a'], cost[i]);
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                min[i][i] = 0;
                for (int j = 0; j < 26; j++) {
                    if (i == j) continue;
                    min[i][j] = Math.min(min[i][j], min[i][k] + min[k][j]);
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < source.length(); i++) {
            long val = min[source.charAt(i) - 'a'][target.charAt(i) - 'a'];

            if (val == MAX) return -1;

            ans += val;
        }

        return ans;
    }
}