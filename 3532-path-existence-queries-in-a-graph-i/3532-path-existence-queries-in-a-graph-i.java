class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        return mySol(n, nums, maxDiff, queries);
    }

    public boolean[] mySol(int n, int[] nums, int maxDiff, int[][] queries) {
        UnionFind uf = new UnionFind(n);

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                uf.merge(i, i - 1);
            }
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            ans[i] = uf.find(u) == uf.find(v);
        }

        return ans;
    }

    class UnionFind {
        int[] parents;

        public UnionFind(int n) {
            parents = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }
        
        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }

        public void merge(int a, int b) {
            a = find(a);
            b = find(b);

            parents[b] = a;
        }
    }
}