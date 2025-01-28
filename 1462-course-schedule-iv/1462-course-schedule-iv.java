class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        return official_topological_sort(numCourses, prerequisites, queries);
    }

    public List<Boolean> official_topological_sort(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, List<Integer>> graph = new HashMap();
        int[] indegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            indegree[edge[1]]++;
        }
        
        Queue<Integer> queue = new LinkedList();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        Map<Integer, Set<Integer>> nodePrerequisites = new HashMap();

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int next : graph.getOrDefault(node, new ArrayList<>())) {
                nodePrerequisites.computeIfAbsent(next, k -> new HashSet()).add(node);

                for (int preOfNode : nodePrerequisites.getOrDefault(node, new HashSet<>())) {
                    nodePrerequisites.computeIfAbsent(next, k -> new HashSet()).add(preOfNode);
                }

                if (--indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        List<Boolean> ans = new ArrayList();

        for (int[] query : queries) {
            ans.add(nodePrerequisites.getOrDefault(query[1], new HashSet()).contains(query[0]));
        }

        return ans;
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