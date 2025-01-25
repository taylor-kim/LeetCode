class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        return mySol(graph);
    }

    public List<Integer> official_dfs(int[][] graph) {
        int n = graph.length;
        boolean[] visit = new boolean[n];
        boolean[] inStack = new boolean[n];

        for (int i = 0; i < n; i++) {
            dfs(i, graph, visit, inStack);
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!inStack[i]) {
                safeNodes.add(i);
            }
        }

        return safeNodes;
    }

    public boolean dfs(
        int node,
        int[][] adj,
        boolean[] visit,
        boolean[] inStack
    ) {
        // If the node is already in the stack, we have a cycle.
        if (inStack[node]) {
            return true;
        }
        if (visit[node]) {
            return false;
        }
        // Mark the current node as visited and part of current recursion stack.
        visit[node] = true;
        inStack[node] = true;
        for (int neighbor : adj[node]) {
            if (dfs(neighbor, adj, visit, inStack)) {
                return true;
            }
        }
        // Remove the node from the stack.
        inStack[node] = false;
        return false;
    }

    public List<Integer> official_topological_sort(int[][] graph) {
        int n = graph.length;
        Map<Integer, List<Integer>> backEdges = new HashMap();
        int[] indgree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int next : graph[i]) {
                backEdges.computeIfAbsent(next, k -> new ArrayList()).add(i);
                indgree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < n; i++) {
            if (indgree[i] == 0) {
                queue.add(i);
            }
        }

        Set<Integer> set = new HashSet();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            set.add(node);

            if (!backEdges.containsKey(node)) continue;

            for (int next : backEdges.get(node)) {
                if (--indgree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        List<Integer> ans = new ArrayList();

        for (int i = 0; i < n; i++) {
            if (set.contains(i)) ans.add(i);
        }

        return ans;
    }

    public List<Integer> mySol(int[][] graph) {
        int n = graph.length;
        Set<Integer> set = new HashSet();
        // Map<Integer, List<Integer>> edges = new HashMap();

        // for (int i = 0; i < n; i++) {
        //     List<Integer> nexts = new ArrayList();
        //     for (int j = 0; j < graph[i].length; j++) {
        //         nexts.add(graph[i][j]);
        //     }

        //     edges.put(i, nexts);

        //     if (nexts.size() == 0) {
        //         set.add(i);
        //     }
        // }

        boolean[] visit = new boolean[n];
        boolean[] inStack = new boolean[n];

        for (int i = 0; i < n; i++) {
            myDfs(i, graph, visit, inStack);
        }

        List<Integer> ans = new ArrayList();

        for (int i = 0; i < n; i++) {
            if (!inStack[i]) ans.add(i);
        }

        return ans;
    }

    private boolean myDfs(int node, int[][] graph, boolean[] visit, boolean[] inStack) {
        if (inStack[node]) return false;

        if (visit[node]) return true;

        visit[node] = true;
        inStack[node] = true;

        for (int next : graph[node]) {
            if (!myDfs(next, graph, visit, inStack)) return false;
        }

        inStack[node] = false;

        return true;
    }
}