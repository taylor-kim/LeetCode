class Solution {
    public int[][] validArrangement(int[][] pairs) {
        return try_iterative(pairs);
    }

    public int[][] try_iterative(int[][] pairs) {
        Map<Integer, Integer> indegree = new HashMap();
        Map<Integer, Integer> outdegree = new HashMap();
        Map<Integer, List<Integer>> edges = new HashMap();
        Set<Integer> nodes = new HashSet();

        for (int[] pair : pairs) {
            indegree.put(pair[1], indegree.getOrDefault(pair[1], 0) + 1);
            outdegree.put(pair[0], outdegree.getOrDefault(pair[0], 0) + 1);

            edges.computeIfAbsent(pair[0], k -> new ArrayList()).add(pair[1]);

            nodes.add(pair[0]);
            nodes.add(pair[1]);
        }

        int start = -1;
        int end = -1;

        for (int node : nodes) {
            int in = indegree.getOrDefault(node, 0);
            int out = outdegree.getOrDefault(node, 0);

            if (in == out) continue;

            if (out - in == 1) start = node;

            if (in - out == 1) end = node;
        }

        if (start == -1) start = pairs[0][0];

        // Stack<Integer> stack = new Stack();
        // Set<Integer> visit = new HashSet();

        // stack.push(start);

        // List<Integer> path = new ArrayList();

        // while (!stack.isEmpty()) {
        //     int node = stack.pop();

        //     if 
        // }
        List<Integer> path = new ArrayList();

        try_dfs(start, edges, path);

        Collections.reverse(path);

        for (int i = 0; i < pairs.length; i++) {
            pairs[i][0] = path.get(i);
            pairs[i][1] = path.get(i + 1);
        }

        return pairs;
    }

    private void try_dfs(int node, Map<Integer, List<Integer>> edges, List<Integer> path) {
        while (edges.containsKey(node) && edges.get(node).size() > 0) {
            int next = edges.get(node).remove(0);

            try_dfs(next, edges, path);
        }

        path.add(node);
    }

    public int[][] after_youtube(int[][] pairs) {
        Map<Integer, Integer> indegree = new HashMap();
        Map<Integer, Integer> outdegree = new HashMap();
        Map<Integer, List<Integer>> edges = new HashMap();
        Set<Integer> nodes = new HashSet();

        for (int[] pair : pairs) {
            indegree.put(pair[1], indegree.getOrDefault(pair[1], 0) + 1);
            outdegree.put(pair[0], outdegree.getOrDefault(pair[0], 0) + 1);
            edges.computeIfAbsent(pair[0], k -> new ArrayList()).add(pair[1]);

            nodes.add(pair[0]);
            nodes.add(pair[1]);
        }

        int start = -1;
        int end = -1;

        for (int node : nodes) {
            int in = indegree.getOrDefault(node, 0);
            int out = outdegree.getOrDefault(node, 0);

            if (in == out) continue;

            // if (in - out != 1) return false;

            // if (out - in != 1) return false;

            if (in - out == 1) {
                // if (end != null) return false;
                end = node;
            }

            if (out - in == 1) {
                // if (start != null) return false;
                start = node;
            }
        }

        // System.out.println(indegree);
        // System.out.println(outdegree);
        // System.out.println(start);
        // System.out.println(end);

        if (start == -1) start = pairs[0][0];

        List<Integer> ans = new ArrayList();

        dfs(start, edges, ans);

        for (int i = ans.size() - 1; i > 0; i--) {
            int current = ans.get(i);
            int next = ans.get(i - 1);

            pairs[ans.size() - i - 1] = new int[] {current, next};
        }

        return pairs;
    }

    private void dfs(int node, Map<Integer, List<Integer>> edges, List<Integer> ans) {
        // System.out.println(node);
        List<Integer> nextNodes = edges.get(node);

        while (nextNodes != null && nextNodes.size() > 0) {
            int next = nextNodes.remove(nextNodes.size() - 1);
            dfs(next, edges, ans);
        }

        ans.add(node);
    }

    public int[][] mySol(int[][] pairs) {
        Map<Integer, Integer> indegree = new HashMap();
        Map<Integer, Integer> outdegree = new HashMap();
        Map<Integer, List<Integer>> edges = new HashMap();
        Map<Integer, List<Integer>> reverseEdges = new HashMap();

        for (int[] pair : pairs) {
            indegree.computeIfAbsent(pair[0], k -> 0);
            indegree.computeIfAbsent(pair[1], k -> 0);
            outdegree.computeIfAbsent(pair[0], k -> 0);
            outdegree.computeIfAbsent(pair[1], k -> 0);

            indegree.put(pair[1], indegree.get(pair[1]) + 1);
            outdegree.put(pair[0], outdegree.get(pair[0]) + 1);

            edges.computeIfAbsent(pair[0], k -> new ArrayList()).add(pair[1]);
            reverseEdges.computeIfAbsent(pair[1], k -> new ArrayList()).add(pair[0]);
        }

        System.out.println(indegree);
        System.out.println(outdegree);
        System.out.println(edges);
        System.out.println(reverseEdges);

        // for (int start : indegree.keySet()) {
        //     if (indegree.get(start) == 0) {
        //         return buildValidPairs(start, edges, pairs.length, true);
        //     }
        // }

        // for (int end : outdegree.keySet()) {
        //     if (outdegree.get(end) == 0) {
        //         return buildValidPairs(end, reverseEdges, pairs.length, false);
        //     }
        // }

        for (int[] pair : pairs) {
            if (indegree.get(pair[0]) < outdegree.get(pair[0])) {
                return buildValidPairs(pair[0], edges, pairs.length, true, indegree, outdegree);
            }

            // if (indegree.get(pair[1]) < outdegree.get(pair[1])) {
            //     return buildValidPairs(pair[1], edges, pairs.length, true);
            // }
        }

        return buildValidPairs(pairs[0][0], edges, pairs.length, true, indegree, outdegree);
    }

    private int[][] buildValidPairs(int start, Map<Integer, List<Integer>> edges, int n, boolean forward,
        Map<Integer, Integer> indegree, Map<Integer, Integer> outdegree) {
        int[][] ans = new int[n][2];
        int index = forward ? 0 : n - 1;

        Queue<Integer> queue = new LinkedList();
        queue.add(start);

        while (!queue.isEmpty() && (forward ? (index < n) : index >= 0)) {
            int node = queue.poll();
            outdegree.put(node, outdegree.get(node) - 1);

            int nextNodeIndex = -1;
            int maxDegree = 0;

            for (int i = 0; i < edges.get(node).size(); i++) {
                int next = edges.get(node).get(i);

                if (indegree.get(next) <= outdegree.get(next) && maxDegree < outdegree.get(next)) {
                    nextNodeIndex = i;
                    maxDegree = outdegree.get(next);
                }
            }

            if (nextNodeIndex < 0) break;

            int next = edges.get(node).remove(nextNodeIndex);

            indegree.put(next, indegree.get(next) - 1);

            if (forward) {
                ans[index][0] = node;
                ans[index++][1] = next;

                queue.add(next);
            } else {
                ans[index][0] = next;
                ans[index--][1] = node;

                queue.add(next);
            }
        }

        return ans;
    }
}