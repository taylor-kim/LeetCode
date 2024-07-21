class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        return official_topoSort(k, rowConditions, colConditions);
    }

    public int[][] official_topoSort(int k, int[][] rc, int[][] cc) {

        int[] rows = buildTopo(k, rc);
        int[] cols = buildTopo(k, cc);

        System.out.println(Arrays.toString(rows));
        System.out.println(Arrays.toString(cols));

        if (rows == null || cols == null) return new int[0][0];

        int[][] ans = new int[k][k];

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (rows[i] == cols[j]) {
                    ans[i][j] = rows[i];
                }
            }
        }

        return ans;
    }

    private int[] buildTopo(int k, int[][] cond) {
        int[] indegree = new int[k + 1];
        Map<Integer, List<Integer>> graph = new HashMap();

        for (int[] edge : cond) {
            graph.computeIfAbsent(edge[0], key -> new ArrayList()).add(edge[1]);
            indegree[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList();

        for (int i = 1; i <= k; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        int[] result = new int[k];
        int index = 0;

        while (!queue.isEmpty()) {
            int num = queue.poll();
            result[index++] = num;

            for (int next : graph.getOrDefault(num, new ArrayList<>())) {
                if (--indegree[next] == 0) queue.add(next);
            }
            k--;
        }

        if (k != 0) return null;

        return result;
    }

    public int[][] mySol_fail(int k, int[][] rc, int[][] cc) {
        int[][] ret = new int[k][k];

        Map<Integer, Integer> rows = buildLine(k, rc);
        Map<Integer, Integer> cols = buildLine(k, cc);

        if (rows == null || cols == null) return new int[0][0];

        // System.out.println(rows);
        // System.out.println(cols);

        for (int num = 1; num <= k; num++) {
            ret[rows.get(num)][cols.get(num)] = num;
        }

        return ret;
    }

    private Map<Integer, Integer> buildLine(int k, int[][] cond) {
        Map<Integer, Integer> graph = new HashMap();
        Map<Integer, Integer> reverse = new HashMap();
        Map<Integer, Integer> indegree = new HashMap();

        Map<Integer, Integer> result = new HashMap();

        Set<Integer> set = new HashSet();

        for (int i = 1; i <= k; i++) set.add(i);

        for (int[] edge : cond) {
            int from = edge[0];
            int to = edge[1];

            if (reverse.containsKey(to)) {
                to = reverse.get(to);
            }

            graph.put(from, to);
            reverse.put(to, from);
            indegree.computeIfAbsent(from, key -> 0);
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);

            // if (graph.getOrDefault(to, 0) == from) return null;
        }

        int index = 0;

        for (int key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                result.put(key, index++);
                set.remove(key);

                while (graph.containsKey(key)) {
                    int next = graph.get(key);
                    result.put(next, index++);
                    set.remove(next);
                    key = next;
                }
            }
        }

        for (int remain : set) {
            result.put(remain, index++);
        }

        return result;
    }
}