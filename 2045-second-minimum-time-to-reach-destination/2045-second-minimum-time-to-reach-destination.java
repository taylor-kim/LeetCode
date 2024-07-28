class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        return tune_mySol_with_editorial(n, edges, time, change);
    }

    public int official_bfs(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> graph = new HashMap();
        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }

        for (int i = 1; i <= n; i++) {
            dist1[i] = dist2[i] = -1;
        }

        Queue<int[]> queue = new LinkedList();
        queue.add(new int[] {1, 1});
        dist1[1] = 0;

        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            int node = data[0];
            int freq = data[1];

            if (!graph.containsKey(node)) continue;

            int timeTaken = freq == 1 ? dist1[node] : dist2[node];

            int count = timeTaken / change;

            if (count % 2 == 0) {
                timeTaken += time;
            } else {
                timeTaken = (count + 1) * change + time;
            }

            for (int next : graph.get(node)) {
                if (dist1[next] == -1) {
                    dist1[next] = timeTaken;
                    queue.add(new int[] {next, 1});
                } else if (dist2[next] == -1 && dist1[next] != timeTaken) {
                    if (next == n) return timeTaken;

                    dist2[next] = timeTaken;
                    queue.add(new int[] {next, 2});
                }
            }
        }

        return -1;
    }

    public int tune_mySol_with_editorial(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> graph = new HashMap();
        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];
        int[] freq = new int[n + 1];

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }

        for (int i = 1; i <= n; i++) {
            dist1[i] = Integer.MAX_VALUE;
            dist2[i] = Integer.MAX_VALUE;
        }

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        queue.add(new int[] {1, 0});
        dist1[1] = 0;

        int prev = 0;
        int now = 0;

        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            int node = data[0];
            int currentTime = data[1];

            freq[node]++;

            if (node == n && freq[node] == 2) {
                return currentTime;
            }

            if (!graph.containsKey(node)) continue;

            int changeCount = currentTime / change;

            if (changeCount % 2 == 0) {
                currentTime = currentTime + time;
            } else {
                currentTime = (changeCount + 1) * change + time;
            }

            for (int next : graph.get(node)) {
                if (freq[next] == 2) continue;

                if (dist1[next] > currentTime) {
                    dist2[next] = dist1[next];
                    dist1[next] = currentTime;
                    queue.add(new int[] {next, currentTime});
                } else if (dist2[next] > currentTime && dist1[next] != currentTime) {
                    dist2[next] = currentTime;
                    queue.add(new int[] {next, currentTime});
                }
            }
        }

        return -1;
    }

    public int mySol_TLE(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> graph = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(edge[0]);
        }

        List<Integer> ans = new ArrayList();
        Set<Integer> set = new HashSet();

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        queue.add(new int[] {1, 0});

        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            int node = data[0];
            int currentTime = data[1];

            if (node == n) {
                if (set.add(currentTime)) {
                    ans.add(currentTime);
                }

                if (ans.size() == 2) return ans.get(1);
            }

            if (!graph.containsKey(node)) continue;

            for (int next : graph.get(node)) {
                int nextTime = currentTime + time;

                int changeCount = currentTime / change;

                if (changeCount % 2 == 0) {
                    queue.add(new int[] {next, nextTime});
                } else {
                    // queue.add(new int[] {next, (changeCount + 1) * change});
                    queue.add(new int[] {next, (changeCount + 1) * change + time});
                }
            }
        }

        return -1;
    }
}