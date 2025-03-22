class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        return mySol(n, edges);
    }

    public int mySol(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);

        uf.print();

        for (int[] edge : edges) {
            uf.merge(edge[0], edge[1]);
        }

        int ans = 0;

        Map<Integer, Integer> components = new HashMap();

        for (int i = 0; i < n; i++) {
            components.put(uf.find(i), components.getOrDefault(uf.find(i), 0) + 1);
        }

        // System.out.println(components);
        // uf.print();

        for (int component : components.keySet()) {
            int expectedEdgeCount = components.get(component) * (components.get(component) - 1) / 2;

            if (uf.edges[component] == expectedEdgeCount) {
                ans++;
            }
        }

        return ans;
    }

    class UnionFind {
        int[] parents;
        int[] edges;

        public UnionFind(int n) {
            parents = new int[n];
            edges = new int[n];

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

            if (a == b) {
                edges[a]++;
                return;
            }

            if (a > b) {
                a += b;
                b = a - b;
                a = a - b;
            }

            parents[b] = a;
            edges[a] += edges[b] + 1;
        }

        public void print() {
            System.out.println(String.format("parents:%s, edges:%s", Arrays.toString(parents), Arrays.toString(edges)));
        }
    }
}