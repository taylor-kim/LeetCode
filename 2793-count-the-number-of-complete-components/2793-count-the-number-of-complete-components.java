class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        return mySol_20260711(n, edges);
    }

    public int mySol_20260711(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            uf.merge(edge[0], edge[1]);
        }

        int ans = 0;

        // uf.print();

        for (int i = 0; i < n; i++) {
            if (uf.find(i) == i && (uf.counts[i] - 1) * uf.counts[i] / 2 == uf.edges[i]) {
                ans++;
            }
        }

        return ans;
    }

    class UnionFind {
        int[] parents;
        int[] edges;
        int[] counts;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            edges = new int[n];
            counts = new int[n];
            ranks = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                counts[i] = 1;
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

            if (a == b) {
                edges[a]++;
                return;
            }

            if (ranks[a] > ranks[b]) {
                parents[b] = a;
                edges[a] += 1 + edges[b];
                counts[a] += counts[b];
            } else {
                parents[a] = b;
                edges[b] += 1 + edges[a];
                counts[b] += counts[a];

                if (ranks[a] == ranks[b]) {
                    ranks[b]++;
                }
            }
        }

        public void print() {
            System.out.println("parents:%s, counts:%s, edges:%s, ranks:%s".formatted(
                Arrays.toString(parents),
                Arrays.toString(counts),
                Arrays.toString(edges),
                Arrays.toString(ranks)
            ));
        }
    }
}