class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        return mySol(numCourses, prerequisites, queries);
    }

    public List<Boolean> mySol(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, Set<Integer>> graph = new HashMap();

        for (int i = 0; i < prerequisites.length; i++) {
            graph.computeIfAbsent(prerequisites[i][0], k -> new HashSet()).add(prerequisites[i][1]);
        }

        List<Boolean> ans = new ArrayList();

        Boolean[][] memo = new Boolean[numCourses][numCourses];

        for (int i = 0; i < queries.length; i++) {
            if (dfs(graph, queries[i][0], queries[i][1], memo)) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }

        return ans;
    }

    private boolean dfs(Map<Integer, Set<Integer>> graph, int node, int target, Boolean[][] memo) {
        if (node == target) return true;
        
        if (memo[node][target] != null) {
            return memo[node][target];
        }

        for (int next : graph.getOrDefault(node, new HashSet<>())) {
            if (dfs(graph, next, target, memo)) {
                return memo[node][target] = true;
            }
        }

        return memo[node][target] = false;
    }
}