class Solution {
    public int minMoves(String[] classroom, int energy) {
        return mySol2_by_hint(classroom, energy);
    }

    public int mySol2_by_hint(String[] mat, int energy) {
        int m = mat.length;
        int n = mat[0].length();
        Map<Integer, Integer> lIdMap = new HashMap();

        int start = -1;
        int lId = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = mat[i].charAt(j);
                if (c == 'X' || c == '.') continue;

                int node = i * n + j;

                if (c == 'S') {
                    start = node;
                } else if (c == 'L') {
                    lIdMap.put(node, lId++);
                }
            }
        }

        int max = ((int)Math.pow(2, lId)) - 1;

        int[][][] bestEnergy = new int[m][n][max + 1];

        for (int[][] arr : bestEnergy) {
            for (int[] row : arr) {
                Arrays.fill(row, -1);
            }
        }

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[3] - b[3];
        });

        pq.add(new int[] {start / n, start % n, energy, 0, 0});

        int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int y = data[0];
            int x = data[1];
            int e = data[2];
            int steps = data[3];
            int mask = data[4];

            // System.out.println("y:%d, x:%d, e:%d, steps:%d".formatted(y, x, e, steps));

            if (mask == max) {
                return steps;
            }

            if (e == 0) continue;

            for (int[] dir : dirs) {
                int ny = y + dir[0];
                int nx = x + dir[1];

                if (ny < 0 || ny >= m || nx < 0 || nx >= n || mat[ny].charAt(nx) == 'X') continue;

                char next = mat[ny].charAt(nx);

                if (next == 'R') {
                    if (bestEnergy[ny][nx][mask] >= energy) continue;
                    bestEnergy[ny][nx][mask] = energy;
                    pq.add(new int[] {ny, nx, energy, steps + 1, mask});
                } else {
                    int nextNode = ny * n + nx;
                    int nextMask = mask;

                    if (lIdMap.containsKey(nextNode)) {
                        nextMask |= (1 << lIdMap.get(nextNode));
                    }

                    int nextE = e - 1;

                    if (bestEnergy[ny][nx][nextMask] >= nextE) continue;

                    bestEnergy[ny][nx][nextMask] = nextE;
                    
                    pq.add(new int[] {ny, nx, nextE, steps + 1, nextMask});
                }
            }
        }

        return -1;
    }

    public int mySol_fail(String[] mat, int energy) {
        int m = mat.length;
        int n = mat[0].length();
        int t = m * n;

        int[][] costs = new int[t][t];

        for (int[] arr : costs) {
            Arrays.fill(arr, t + 1);
        }

        Map<Integer, List<Integer>> graph = new HashMap();

        int start = -1;
        int lCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = mat[i].charAt(j);
                if (c == 'X' || c == '.') continue;

                int startNode = i * m + j;

                if (c == 'S') start = startNode;

                if (c == 'L') lCount++;

                System.out.println(startNode);

                dfs(mat, graph, costs, i, j, i, j, 0, new boolean[t]);
            }
        }

        System.out.println(graph);

        for (int[] row : costs) {
            System.out.println(Arrays.toString(row));
        }

        Queue<int[]> queue = new LinkedList();
        queue.add(new int[] {start, 0, 0, energy});

        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            int node = data[0];
            int cost = data[1];
            int collect = data[2];
            int remainEnergy = data[3];

            if (collect == lCount) {
                return cost;
            }

            for (int nextNode : graph.getOrDefault(node, Collections.emptyList())) {
                int i = nextNode / n;
                int j = nextNode % n;
                char c = mat[i].charAt(j);

                if (c == 'L') {
                    // if (remainEnergy);
                } else if (c == 'R') {
                    
                }
            }
        }

        return -1;
    }

    private void dfs(String[] mat, Map<Integer, List<Integer>> graph, int[][] costs, 
                int baseI, int baseJ, int i, int j, int cost, boolean[] visit) {
        int m = mat.length;
        int n = mat[0].length();

        if (i < 0 || i >= m || j < 0 || j >= n) return;

        int node = i * n + j;

        if (visit[node]) return;

        visit[node] = true;

        char c = mat[i].charAt(j);

        if (c  == 'X') return;

        int startNode = baseI * n + baseJ;

        if (startNode == node) {
            costs[node][node] = 0;
        } else if (node != '.') {
            costs[startNode][node] = cost;
            // costs[node][startNode] = cost;

            graph.computeIfAbsent(startNode, k -> new ArrayList()).add(node);
            // graph.computeIfAbsent(node, k -> new ArrayList()).add(startNode);
        }

        int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };

        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            dfs(mat, graph, costs, baseI, baseJ, ni, nj, cost + 1, visit);
        }
    }
}