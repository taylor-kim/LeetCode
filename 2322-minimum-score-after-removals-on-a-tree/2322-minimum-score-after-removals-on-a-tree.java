class Solution {
    public int minimumScore(int[] nums, int[][] edges) {
        return official_enumerate(nums, edges);
    }

    public int official_enumerate(int[] nums, int[][] edges) {
        int n = nums.length;
        int[] counter = {0};
        int[] in = new int[n];
        int[] out = new int[n];
        int[] sum = new int[n];
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int root = 1;

        dfs(root, -1, nums, graph, in, out, counter, sum);

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (i == root) continue;
            for (int j = i + 1; j < n; j++) {
                if (j == root) continue;
                // System.out.println(String.format("i:%d, j:%d", i, j));

                // if (in[i] < in[j] && in[j] >= out[i]) {
                //     System.out.println(String.format("in[%d]:%d, in[%d]:%d, out[%d]:%d, out[%d]:%d"
                //     , i, in[i], j, in[j], i, out[i], j, out[j]));
                // }

                // if (in[i] < in[j] && in[j] < out[i]) {
                if (in[i] < in[j] && out[j] <= out[i]) {
                    ans = Math.min(ans, 
                        Math.max(sum[i] ^ sum[j], Math.max(sum[j], sum[root] ^ sum[i]))
                        - Math.min(sum[i] ^ sum[j], Math.min(sum[j], sum[root] ^ sum[i]))
                        );
                // } else if (in[j] < in[i] && in[i] < out[j]) {
                } else if (in[j] < in[i] && out[i] <= out[j]) {
                    ans = Math.min(ans, 
                        Math.max(sum[i] ^ sum[j], Math.max(sum[i], sum[root] ^ sum[j]))
                        - Math.min(sum[i] ^ sum[j], Math.min(sum[i], sum[root] ^ sum[j]))
                        );
                } else {
                    ans = Math.min(ans, 
                        Math.max(sum[i], Math.max(sum[j], sum[root] ^ sum[i] ^ sum[j]))
                        - Math.min(sum[i], Math.min(sum[j], sum[root] ^ sum[i] ^ sum[j]))
                        );
                }
            }
        }

        return ans;
    }

    private int dfs(int node, int parent, int[] nums, List<Integer>[] graph, int[] in, int[] out, int[] counter, int[] sum) {
        in[node] = counter[0]++;

        sum[node] = nums[node];

        for (int next : graph[node]) {
            if (next == parent) continue;

            sum[node] ^= dfs(next, node, nums, graph, in, out, counter, sum);
        }

        out[node] = counter[0];

        return sum[node];
    }

    int res = Integer.MAX_VALUE;

    public int try_official_double_dfs(int[] nums, int[][] edges) {
        int n = nums.length;
        int sum = 0;
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList();
            sum ^= nums[i];
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        dfs(0, -1, nums, graph, sum);

        return res;
    }

    private int dfs(int node, int parent, int[] nums, List<Integer>[] graph, int sum) {
        int son = nums[node];

        for (int next : graph[node]) {
            if (next == parent) continue;

            son ^= dfs(next, node, nums, graph, sum);
        }

        for (int next : graph[node]) {
            if (next == parent) {
                dfs2(next, node, son, node, nums, graph, sum);
            }
        }

        return son;
    }

    private int dfs2(int node, int parent, int other, int anc, int[] nums, List<Integer>[] graph, int sum) {
        int son = nums[node];

        for (int next : graph[node]) {
            if (next == parent) continue;

            son ^= dfs2(next, node, other, anc, nums, graph, sum);
        }

        if (parent == anc) return son;

        res = Math.min(res, 
            Math.max(son, Math.max(other, sum ^ son ^ other)) 
            - Math.min(son, Math.min(other, sum ^ son ^ other)));

        return son;
    }

    public int others_good(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] graph = new ArrayList[n];
        int[] subtreeXor = new int[n];
        Set[] descendants = new Set[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList();
            descendants[i] = new HashSet();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        dfs(0, -1, nums, graph, subtreeXor, descendants);

        int totalXor = subtreeXor[0];

        int ans = Integer.MAX_VALUE;

        // System.out.println(totalXor + ", " + Arrays.toString(subtreeXor));

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x, y, z;

                if (descendants[i].contains(j)) {
                    x = subtreeXor[j];
                    y = subtreeXor[i] ^ x;
                    z = totalXor ^ x ^ y;
                } else if (descendants[j].contains(i)) {
                    x = subtreeXor[i];
                    y = subtreeXor[j] ^ x;
                    z = totalXor ^ x ^ y;
                } else {
                    x = subtreeXor[i];
                    y = subtreeXor[j];
                    z = totalXor ^ x ^ y;
                }

                ans = Math.min(ans, Math.max(x, Math.max(y, z)) - Math.min(x, Math.min(y, z)));
            }
        }

        return ans;
    }

    private int dfs(int node, int parent, int[] nums, List<Integer>[] graph, int[] subtreeXor, Set<Integer>[] descendants) {
        subtreeXor[node] = nums[node];
        descendants[node].add(node);

        for (int next : graph[node]) {
            if (next == parent) continue;

            subtreeXor[node] ^= dfs(next, node, nums, graph, subtreeXor, descendants);
            descendants[node].addAll(descendants[next]);
        }

        return subtreeXor[node];
    }

    public int mySol_fail(int[] nums, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap();

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new HashSet()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new HashSet()).add(edge[0]);
        }

        int[][] xors = getXors(nums, edges, graph);

        int totalXor = 0;

        for (int num : nums) {
            totalXor ^= num;
        }

        System.out.println(totalXor + "\n");

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];

            int[] xor = xors[i];

            System.out.println(String.format("edge:%s, left:%d, right:%d", Arrays.toString(edges[i]), xor[0], xor[1]));
        }

        return 0;
    }

    private int[][] getXors(int[] nums, int[][] edges, Map<Integer, Set<Integer>> graph) {
        int[][] xors = new int[edges.length][2];

        for (int[] xor : xors) {
            Arrays.fill(xor, -1);
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];

            graph.get(edge[0]).remove(edge[1]);
            graph.get(edge[1]).remove(edge[0]);

            int a = edge[0];
            int b = edge[1];

            boolean[] visit = new boolean[nums.length];

            xors[i][0] = getXor(Math.min(a, b), graph, nums, visit);
            xors[i][1] = getXor(Math.max(a, b), graph, nums, visit);

            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return xors;
    }

    private int getXor(int node, Map<Integer, Set<Integer>> graph, int[] nums, boolean[] visit) {
        if (visit[node]) return 0;

        visit[node] = true;
        int ans = nums[node];

        for (int next : graph.getOrDefault(node, new HashSet<>())) {
            ans ^= getXor(next, graph, nums, visit);
        }

        visit[node] = false;

        return ans;
    }
}