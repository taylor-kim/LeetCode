class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
        return others_sol(nums);
    }

    public boolean others_sol(int[] nums) {
        int n = nums.length;

        if (n == 1) return true;

        // Graph g = new BFS(n);
        Graph g = new DFS(n);
        // Graph g = new UnionFind(n);
        Map<Integer, Integer> seen = new HashMap();

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) return false;

            List<Integer> primes = getPrimeFactors(nums[i]);

            // System.out.println(String.format("num:%d, primeFactors:%s", nums[i], primes));

            for (int p : primes) {
                if (seen.containsKey(p)) {
                    g.addEdge(seen.get(p), i);
                } else {
                    seen.put(p, i);
                }
            }
        }

        return g.isConnected();
    }

    private List<Integer> getPrimeFactors(int x) {
        List<Integer> primeFactors = new ArrayList();

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                primeFactors.add(i);

                while (x % i == 0) {
                    x /= i;
                }
            }
        }
        if (x != 1) {
            primeFactors.add(x);
        }

        return primeFactors;
    }

    public interface Graph {
        void addEdge(int i, int j);
        boolean isConnected();
    }

    public class DFS implements Graph {
        protected int n;
        protected Map<Integer, List<Integer>> edges;
        protected boolean[] visited;

        public DFS(int n) {
            this.n = n;
            edges = new HashMap();
            visited = new boolean[n];
        }

        public void addEdge(int i, int j) {
            edges.computeIfAbsent(i, k -> new ArrayList()).add(j);
            edges.computeIfAbsent(j, k -> new ArrayList()).add(i);
        }

        public boolean isConnected() {
            traverse(0);

            for (boolean b : visited) {
                if (!b) return false;
            }

            return true;
        }

        void traverse(int index) {
            if (index >= n || visited[index]) return;

            visited[index] = true;

            if (!edges.containsKey(index)) return;

            for (int next : edges.get(index)) {
                traverse(next);
            }
        }
    }

    public class BFS extends DFS {
        public BFS(int n) {
            super(n);
        }

        void traverse(int index) {
            Queue<Integer> queue = new LinkedList();
            queue.add(index);

            while (!queue.isEmpty()) {
                int node = queue.poll();
                // visited[node] = true;

                if (!edges.containsKey(node)) {
                    continue;
                }

                for (int next : edges.get(node)) {
                    if (!visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
        }

        void traverse_others(int x) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(x);
            while (!q.isEmpty()) {
                x = q.poll();
                if (!edges.containsKey(x)) {
                    continue;
                }
                for (int y: edges.get(x)) {
                    if (!visited[y]) {
                        q.add(y);
                        visited[y] = true;
                    }
                }
            }
        }
    }

    public class UnionFind implements Graph {
        private int n;
        private int[] parents;
        private int[] ranks;

        public UnionFind(int n) {
            this.n = n;
            parents = new int[n];
            ranks = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        private int find(int num) {
            if (parents[num] != num) {
                parents[num] = find(parents[num]);
            }

            return parents[num];
        }

        private void merge(int a, int b) {
            a = find(a);
            b = find(b);

            if (a != b) {
                if (ranks[a] < ranks[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }

                parents[b] = a;
                ranks[a] += ranks[b];
            }
        }

        public void addEdge(int i, int j) {
            merge(i, j);
        }

        public boolean isConnected() {
            return ranks[find(0)] == n;
        }
    }
}