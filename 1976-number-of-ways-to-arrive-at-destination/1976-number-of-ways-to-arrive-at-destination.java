class Solution {
    public int countPaths(int n, int[][] roads) {
        return mySol_tle(n, roads);
    }

    public int mySol2(int n, int[][] roads) {
        int ans = 0;

        int[][] costs = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    costs[i][j] = 0;
                } else {
                    costs[i][j] = (int)1e9 + 1;
                }
            }
        }

        for (int[] road : roads) {
            costs[road[0]][road[1]] = road[2];
            costs[road[1]][road[0]] = road[2];
        }

        for (int[] cost : costs) {
            System.out.println(Arrays.toString(cost));
        }

        int mod = (int)1e9 + 7;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                // if (i == k) continue;
                for (int j = 0; j < n; j++) {
                    // if (i == k || i == j || j == k) continue;

                    if (i == 0 && j == n - 1) {
                        if (costs[i][j] == costs[i][k] + costs[k][j]) {
                            ans++;
                        } else if (costs[i][j] > costs[i][k] + costs[k][j]) {
                            ans = 1;
                            costs[i][j] = costs[i][k] + costs[k][j];
                        }
                    } else {
                        costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                    }
                }
            }
        }

        for (int[] cost : costs) {
            System.out.println(Arrays.toString(cost));
        }

        return ans;
    }

    public int mySol_tle(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph = new HashMap();

        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], k -> new ArrayList()).add(new int[] {road[1], road[2]});
            graph.computeIfAbsent(road[1], k -> new ArrayList()).add(new int[] {road[0], road[2]});
        }

        Queue<long[]> queue = new PriorityQueue<>((a, b) -> {
            return Long.compare(a[1], b[1]);
        });

        queue.add(new long[] {0, 0});

        long[] minCosts = new long[n];
        int[] pathCounts = new int[n];
        pathCounts[0] = 1;

        Arrays.fill(minCosts, Long.MAX_VALUE);

        int ans = 0;
        int mod = (int)1e9 + 7;

        while (!queue.isEmpty()) {
            long[] data = queue.poll();

            int node = (int)data[0];
            long cost = data[1];

            if (minCosts[node] < cost) continue;

            for (int next[] : graph.getOrDefault(node, new ArrayList<>())) {
                if (next[1] + cost < minCosts[next[0]]) {
                    minCosts[next[0]] = next[1] + cost;
                    pathCounts[next[0]] = pathCounts[node];

                    queue.add(new long[] {next[0], next[1] + cost});
                } else if (next[1] + cost == minCosts[next[0]]) {
                    pathCounts[next[0]] = (pathCounts[next[0]] + pathCounts[node]) % mod;
                }
            }
        }

        return pathCounts[n - 1];
    }
}