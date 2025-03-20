class Solution {
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        return mySol(n, edges, query);
    }

    public int[] mySol(int n, int[][] edges, int[][] query) {
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            uf.merge(edge[0], edge[1], edge[2]);
        }

        int[] ans = new int[query.length];

        for (int i = 0; i < query.length; i++) {
            int[] q = query[i];

            if (uf.find(q[0]) == uf.find(q[1])) {
                ans[i] = uf.costs[uf.find(q[0])];
            } else {
                ans[i] = -1;
            }
        }

        return ans;
    }

    class UnionFind {
        private int[] parents;
        private int[] costs;

        public UnionFind(int n) {
            parents = new int[n];
            costs = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                costs[i] = Integer.MAX_VALUE;
            }
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }
        
        public void merge(int a, int b, int cost) {
            // print(String.format("merge %d and %d - costs[%d]:%d & cost:%d", a, b, a, costs[a], cost));

            // print(String.format("merge %d and %d - costs[%d]:%d & cost:%d\n", a, b, a, costs[a], cost));

            // cost &= costs[a] & costs[b];

            a = find(a);
            b = find(b);

            cost &= costs[a] & costs[b];

            // if (a == b) return;

            if (a > b) {
                a += b;
                b = a - b;
                a = a - b;
            }

            parents[b] = a;
            costs[a] &= cost;
        }
    }

    private void print(String s) {
        System.out.println(s);
    }
}