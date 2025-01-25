class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        return official_topological_sort(graph);
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
        Set<Integer> set = new HashSet();
        Map<Integer, List<Integer>> edges = new HashMap();

        for (int i = 0; i < graph.length; i++) {
            List<Integer> nexts = new ArrayList();
            for (int j = 0; j < graph[i].length; j++) {
                nexts.add(graph[i][j]);
            }

            edges.put(i, nexts);

            if (nexts.size() == 0) {
                set.add(i);
            }
        }

        Boolean[] memo = new Boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!set.contains(i)) {
                if (dfs(i, edges, new HashSet(), memo)) {
                    set.add(i);
                }
            }
        }

        List<Integer> ans = new ArrayList();

        for (int i = 0; i < graph.length; i++) {
            if (set.contains(i)) ans.add(i);
        }

        return ans;
    }

    private boolean dfs(int node, Map<Integer, List<Integer>> edges, Set<Integer> visit, Boolean[] memo) {
        // if (term.contains(node) || visit.add(node)) return true;
        if (memo[node] != null) {
            return memo[node];
        }
        
        if (!visit.add(node)) return false;

        boolean ans = true;

        if (edges.get(node).size() == 0) {
            ans = true;
        } else {
            for (int next : edges.get(node)) {
                ans &= dfs(next, edges, visit, memo);
            }
        }

        visit.remove(node);

        return memo[node] = ans;
    }
}