class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        return mySol2(parent, s);
    }

    public int[] mySol2(int[] parent, String s) {
        int n = parent.length;

        Map<Integer, List<Integer>> graph = new HashMap();

        for (int i = 0; i < n; i++) {
            if (parent[i] < 0) continue;

            graph.computeIfAbsent(parent[i], k -> new ArrayList()).add(i);
        }

        int[] nearest = new int[26];

        Arrays.fill(nearest, -1);

        int[] ans = new int[n];

        topdown2(graph, 0, s, nearest, parent, ans);

        return ans;
    }

    private void topdown2(Map<Integer, List<Integer>> graph, int node, String s, int[] nearest, int[] parent, int[] ans) {
        int c = s.charAt(node) - 'a';

        if (nearest[c] >= 0) {
            parent[node] = nearest[c];
        }

        int old = nearest[c];

        nearest[c] = node;

        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            topdown2(graph, next, s, nearest, parent, ans);
        }

        nearest[c] = old;

        ans[node] += 1;

        if (parent[node] >= 0) {
            ans[parent[node]] += ans[node];
        }
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