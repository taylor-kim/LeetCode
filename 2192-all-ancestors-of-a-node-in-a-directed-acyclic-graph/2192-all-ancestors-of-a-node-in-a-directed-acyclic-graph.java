class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        return mySol(n, edges);
    }

    public List<List<Integer>> mySol(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap();
        int[] dep = new int[n];

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(edge[1]);
            dep[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList();

        List<List<Integer>> result = new ArrayList();
        List<Set<Integer>> allParents = new ArrayList();

        for (int i = 0; i < n; i++) {
            if (dep[i] == 0) queue.add(i);

            result.add(new ArrayList());
            allParents.add(new HashSet());
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int node = queue.poll();

                if (!graph.containsKey(node)) {
                    continue;
                }

                for (int next : graph.get(node)) {
                    allParents.get(next).addAll(allParents.get(node));
                    allParents.get(next).add(node);

                    if (--dep[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            result.get(i).addAll(allParents.get(i));
            Collections.sort(result.get(i));
        }

        return result;
    }

    // private void dfs(int node, Map<Integer, List<Integer>> reverse, List<List<Integer>> result, List<Integer> list) {
    //     if (reverse.containsKey(node)) {
    //         for (int before : reverse.get(node)) {
    //             list.add(before);
    //         }
    //     }
    // }
}