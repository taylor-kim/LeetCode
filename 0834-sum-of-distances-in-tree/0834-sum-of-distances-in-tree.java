class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        return others_lee(n, edges);
    }

    int[] res, count;
    ArrayList<HashSet<Integer>> tree;

    public int[] others_lee(int N, int[][] edges) {
        tree = new ArrayList<HashSet<Integer>>();
        res = new int[N];
        count = new int[N];
        for (int i = 0; i < N ; ++i)
            tree.add(new HashSet<Integer>());
        for (int[] e : edges) {
            tree.get(e[0]).add(e[1]);
            tree.get(e[1]).add(e[0]);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return res;
    }

    public void dfs(int root, int pre) {
        for (int i : tree.get(root)) {
            if (i == pre) continue;
            dfs(i, root);
            count[root] += count[i];
            res[root] += res[i] + count[i];
        }
        count[root]++;
    }


    public void dfs2(int root, int pre) {
        for (int i : tree.get(root)) {
            if (i == pre) continue;
            res[i] = res[root] - count[i] + count.length - count[i];
            dfs2(i, root);
        }
    }

    public int[] mySol(int n, int[][] edges) {
        int[][] distances = new int[n][n];

        for (int[] row : distances) {
            Arrays.fill(row, n);
        }

        for (int i = 0; i < n; i++) {
            distances[i][i] = 0;
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            distances[a][b] = 1;
            distances[b][a] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }

        // for (int[] row : distances) {
        //     System.out.println(Arrays.toString(row));
        // }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int sum = 0;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    sum += distances[i][j];
                }
            }
            ans[i] = sum;
        }

        return ans;
    }
}