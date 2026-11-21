class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        return mySol(parent, s);
    }

    public int[] mySol(int[] parent, String s) {
        int n = parent.length;

        Map<Integer, List<Integer>> graph = new HashMap();

        for (int i = 0; i < n; i++) {
            if (parent[i] < 0) continue;

            graph.computeIfAbsent(parent[i], k -> new ArrayList()).add(i);
        }

        int[] nearest = new int[26];

        Arrays.fill(nearest, -1);

        topdown(graph, 0, s, nearest, parent);

        // System.out.println(Arrays.toString(parent));

        graph.clear();

        for (int i = 0; i < n; i++) {
            if (parent[i] < 0) continue;

            graph.computeIfAbsent(parent[i], k -> new ArrayList()).add(i);
        }

        int[] ans = new int[n];

        count(graph, 0, ans);

        return ans;
    }

    private int count(Map<Integer, List<Integer>> graph, int node, int[] ans) {
        int children = 1;

        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            children += count(graph, next, ans);
        }

        return ans[node] = children;
    }

    private void topdown(Map<Integer, List<Integer>> graph, int node, String s, int[] nearest, int[] parent) {
        int c = s.charAt(node) - 'a';

        if (nearest[c] >= 0) {
            parent[node] = nearest[c];
        }

        int old = nearest[c];

        nearest[c] = node;

        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            topdown(graph, next, s, nearest, parent);
        }

        nearest[c] = old;
    }
}