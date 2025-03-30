class Solution {
    private void dfs(HashMap<String,HashMap<String,Double>> gr, String src, String dst, double[] cur, double temp, HashSet<String> vis) {
    if (vis.contains(src)) return; // \U0001f504 Avoid cycles
    vis.add(src);

    if (src.equals(dst)) { // \U0001f3af Found target
        cur[0] = temp;
        return;
    }

    for (Map.Entry<String, Double> edge : gr.get(src).entrySet()) { // \U0001f504 Visit neighbors
        String curVal = edge.getKey();
        double val = edge.getValue();
        dfs(gr, curVal, dst, cur, temp * val, vis);
    }
}

private HashMap<String, HashMap<String, Double>> createGraph(List<List<String>> equations, double[] values) {
    HashMap<String, HashMap<String, Double>> gr = new HashMap<>();
    int n = equations.size();

    for (int i = 0; i < n; i++) {
        String src = equations.get(i).get(0);
        String dst = equations.get(i).get(1);
        double ans = values[i];

        gr.putIfAbsent(src, new HashMap<>());
        gr.putIfAbsent(dst, new HashMap<>());

        gr.get(src).put(dst, ans);         // ➗ Forward relation
        gr.get(dst).put(src, 1.0 / ans);   // \U0001f504 Reverse relation
    }

    return gr;
}

public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    HashMap<String, HashMap<String, Double>> gr = createGraph(equations, values);
    int n = queries.size();
    double[] ans = new double[n];

    for (int i = 0; i < n; i++) {
        String src = queries.get(i).get(0);
        String dst = queries.get(i).get(1);

        if (!gr.containsKey(src) || !gr.containsKey(dst)) { // ⚠️ Variables not present
            ans[i] = -1.0;
        } else {
            HashSet<String> vis = new HashSet<>();
            double[] cur = { -1.0 };
            double temp = 1.0;
            dfs(gr, src, dst, cur, temp, vis);
            ans[i] = cur[0];
        }
    }

    return ans;
}
}