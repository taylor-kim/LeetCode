class Solution {
    public int minScore(int n, int[][] roads) {
        return mySol(n, roads);
    }

    public int othersUf(int n, int[][] roads) {
        UnionFind uf = new UnionFind(n + 1);
        
        for (int[] road : roads) {
            uf.merge(road[0], road[1]);
        }

        int ans = Integer.MAX_VALUE;

        for (int[] road : roads) {
            if (uf.find(road[0]) == uf.find(n)) {
                ans = Math.min(ans, road[2]);
            }
        }

        return ans;
    }

    public class UnionFind {
        int[] parents;

        public UnionFind(int n) {
            parents = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }

        public void merge(int a, int b) {
            a = find(a);
            b = find(b);

            parents[a] = b;
        }
    }

    public int mySol(int n, int[][] roads) {
        return bfs(n, roads);
    }

    public int dfs(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph = new HashMap();

        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], k -> new ArrayList()).add(new int[] {road[1], road[2]});
            graph.computeIfAbsent(road[1], k -> new ArrayList()).add(new int[] {road[0], road[2]});
        }

        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        return topdown(graph, 1, costs, costs[1]);
    }

    public int topdown(Map<Integer, List<int[]>> graph, int node, int[] costs, int cost) {
        int ans = cost;

        for (int[] next : graph.get(node)) {
            int nextNode = next[0];
            int nextCost = next[1];

            if (costs[nextNode] > nextCost) {
                costs[nextNode] = nextCost;

                ans = Math.min(ans, topdown(graph, nextNode, costs, nextCost));
            }
        }

        return ans;
    }

    public int bfs(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph = new HashMap();

        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], k -> new ArrayList()).add(new int[] {road[1], road[2]});
            graph.computeIfAbsent(road[1], k -> new ArrayList()).add(new int[] {road[0], road[2]});
        }

        Queue<Integer> queue = new LinkedList();
        queue.add(1);

        int ans = Integer.MAX_VALUE;

        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (!graph.containsKey(node)) continue;

            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int nextCost = next[1];

                if (costs[nextNode] <= nextCost) continue;

                costs[nextNode] = nextCost;

                ans = Math.min(ans, nextCost);

                queue.add(nextNode);
            }
        }

        return ans;
    }
}