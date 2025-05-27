class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        return others_dfs(colors, edges);
    }

    public int others_dfs(String colors, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap();

        for (int[] edge : edges) {
            if (edge[0] == edge[1]) return -1;

            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
        }

        int n = colors.length();
        int[] visit = new int[n];
        int[][] memo = new int[n][26];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int sub = dfs(i, colors, graph, visit, memo);
            if (sub < 0) return -1;
            ans = Math.max(ans, sub);
        }

        return ans;
    }

    private int dfs(int node, String colors, Map<Integer, List<Integer>> graph, int[] visit, int[][] memo) {
        if (visit[node] == 1) return Integer.MIN_VALUE;

        if (visit[node] == 2) return memo[node][colors.charAt(node) - 'a'];

        visit[node] = 1;

        int ans = 0;

        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            int sub = dfs(next, colors, graph, visit, memo);

            if (sub < 0) return sub;

            for (int i = 0; i < 26; i++) {
                memo[node][i] = Math.max(memo[node][i], memo[next][i]);
            }
        }

        visit[node] = 2;

        return ++memo[node][colors.charAt(node) - 'a'];
    }

    public int mySol2(String colors, int[][] edges) {
        int n = colors.length();
        int ans = 0;

        Map<Integer, List<Integer>> graph = new HashMap();
        int[] indegree = new int[n];

        for (int[] edge : edges) {
            if (edge[0] == edge[1]) return -1;

            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            indegree[edge[1]]++;
        }

        boolean[] visit = new boolean[n];
        boolean[] stack = new boolean[n];

        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            if (!visit[i] && hasCycle(0, graph, visit, stack, uf)) {
                return -1;
            }
        }

        Queue<Data> queue = new LinkedList();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(new Data(i, new int[26], new boolean[n], n));
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                Data d = queue.poll();

                d.increase(colors.charAt(d.node) - 'a');
                d.remain--;

                ans = Math.max(ans, d.currentMax);

                if (!d.isPossible(ans)) {
                    continue;
                }

                for (int next : graph.getOrDefault(d.node, new ArrayList<>())) {
                    if (d.stack[next]) return -1;

                    queue.add(new Data(next, d.count.clone(), d.stack.clone(), d.remain));
                }
            }
        }

        return ans;
    }

    public class Data {
        int node;
        int[] count;
        boolean[] stack;
        int currentMax;
        int remain;

        public Data(int node, int[] count, boolean[] stack, int remain) {
            this.node = node;
            this.count = count;
            this.stack = stack;
            this.remain = remain;

            stack[node] = true;
        }

        public void increase(int color) {
            currentMax = Math.max(currentMax, ++count[color]);
        }

        public boolean isPossible(int max) {
            return max < currentMax + remain;
        }
    }

    public int mySol_fail(String colors, int[][] edges) {
        int n = colors.length();
        int ans = 0;

        Map<Integer, List<Integer>> graph = new HashMap();
        int[] indegree = new int[n];

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
        }

        boolean[] visit = new boolean[n];
        boolean[] stack = new boolean[n];

        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            if (!visit[i] && hasCycle(0, graph, visit, stack, uf)) {
                return -1;
            }
        }

        uf.print();
        Map<Integer, List<Integer>> nodesMap = new HashMap();

        for (int i = 0; i < n; i++) {
            nodesMap.computeIfAbsent(uf.find(i), k -> new ArrayList()).add(i);
        }

        for (List<Integer> nodes : nodesMap.values()) {
            int[] count = new int[26];

            for (int node : nodes) {
                int color = colors.charAt(node) - 'a';

                ans = Math.max(ans, ++count[color]);

                System.out.println(String.format("node:%d of %d, color:%d, colorCount:%d", node, nodes.size(), color, count[color]));
            }
        }

        return ans;
    }

    private boolean hasCycle(int node, Map<Integer, List<Integer>> graph, boolean[] visit, boolean[] stack, UnionFind uf) {
        if (stack[node]) return true;

        if (visit[node]) return false;

        stack[node] = true;
        visit[node] = true;

        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            uf.merge(node, next);

            if (stack[next] 
                || (!visit[next] && hasCycle(next, graph, visit, stack, uf))) return true;
        }

        stack[node] = false;

        return false;
    }

    class UnionFind {
        private int[] parents;
        private int component;
        
        public UnionFind(int n) {
            parents = new int[n];
            component = n;

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

            if (a == b) return;

            if (a > b) {
                a += b;
                b = a - b;
                a = a - b;
            }

            parents[b] = a;

            component--;
        }

        public void print() {
            System.out.println(String.format("component:%d", component));
            System.out.println(String.format("parents:%s", Arrays.toString(parents)));
        }
    }
}