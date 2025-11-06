class Solution {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        return mySol(c, connections, queries);
    }

    public int[] mySol(int c, int[][] connections, int[][] queries) {
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();

        UnionFind uf = new UnionFind(c + 1);

        for (int[] edge : connections) {
            uf.merge(edge[0], edge[1]);
        }

        for (int i = 1; i <= c; i++) {
            int group = uf.find(i);

            map.computeIfAbsent(group, k -> new TreeSet<>()).add(i);
        }

        List<Integer> ans = new ArrayList();

        Set<Integer> offLines = new HashSet();

        for (int[] q : queries) {
            int x = q[1];
            if (q[0] == 1) {
                if (offLines.contains(x)) {
                    if (map.containsKey(uf.find(x))) {
                        ans.add(map.get(uf.find(x)).first());
                    } else {
                        ans.add(-1);
                    }
                } else {
                    ans.add(x);
                }
            } else {
                if (map.containsKey(uf.find(x))) {
                    map.get(uf.find(x)).remove(x);
                    if (map.get(uf.find(x)).isEmpty()) {
                        map.remove(uf.find(x));
                    }
                    offLines.add(x);
                }
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    class UnionFind {
        int[] parents;

        public UnionFind(int n) {
            parents = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public void merge(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) return;

            if (a > b) {
                a += b;
                b = a - b;
                a = a - b;
            }

            parents[b] = a;
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }
    }
}