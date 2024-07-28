class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        return try_dijkstra(source, target, original, changed, cost);
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