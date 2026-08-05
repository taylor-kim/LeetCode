class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        return mySol(n, k, invocations);
    }

    public List<Integer> mySol(int n, int k, int[][] invocations) {
        Map<Integer, List<Integer>> graph = new HashMap();
        int[] indegree = new int[n];
        UnionFind uf = new UnionFind(n);

        for (int[] inv : invocations) {
            graph.computeIfAbsent(inv[0], key -> new ArrayList()).add(inv[1]);
            indegree[inv[1]]++;
        }

        Queue<Integer> queue = new LinkedList();
        queue.add(k);

        // System.out.println(Arrays.toString(indegree));

        Set<String> visit = new HashSet();


        while (!queue.isEmpty()) {
            int node = queue.poll();
            // System.out.println("node:%d".formatted(node));

            if (!graph.containsKey(node)) continue;

            for (int next : graph.get(node)) {
                if (visit.add(node + "_" + next) && indegree[next] > 0) {
                    indegree[next]--;
                    uf.merge(node, next);
                    queue.add(next);
                }
            }
        }

        // 0 -> 1 <-> 2

        // System.out.println(Arrays.toString(uf.parents));
        // System.out.println(Arrays.toString(indegree));

        List<Integer> all = IntStream.range(0, n).boxed().toList();
        Set<Integer> suspicious = new HashSet();

        for (int i = 0; i < n; i++) {
            if (uf.find(i) == uf.find(k)) {
                if (indegree[i] > 0) {
                    return all;
                } else {
                    suspicious.add(i);
                }
            }
            // if (uf.find(i) != uf.find(k) || indegree[i] > 0) {
            //     ans.add(i);
            // }
        }

        List<Integer> ans = new ArrayList();

        for (int i = 0; i < n; i++) {
            if (!suspicious.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }

    class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];

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

            if (a == b) return;

            if (ranks[a] > ranks[b]) {
                parents[b] = a;
            } else {
                parents[a] = b;

                if (ranks[a] == ranks[b]) {
                    ranks[b]++;
                }
            }
        }
    }
}