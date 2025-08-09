class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
        return practice_20250809(nums);
    }

    public boolean practice_20250809(int[] nums) {
        int n = nums.length;

        if (n == 1) return true;

        Map<Integer, List<Integer>> map = new HashMap();
        Map<Integer, Integer> seen = new HashMap();

        Map<Integer, Set<Integer>> graph = new HashMap();
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            if (num == 1) return false;

            List<Integer> primes = map.computeIfAbsent(num, k -> getPrimeFactors(num));

            for (int p : primes) {
                if (seen.containsKey(p)) {
                    int j = seen.get(p);

                    graph.computeIfAbsent(i, k -> new HashSet()).add(j);
                    graph.computeIfAbsent(j, k -> new HashSet()).add(i);

                    uf.merge(i, j);
                } else {
                    seen.put(p, i);
                }
            }
        }

        boolean[] visit = new boolean[n];

        dfs(0, graph, visit);

        for (boolean b : visit) {
            if (!b) return false;
        }

        return true;

        // return uf.isConnected();
    }

    private class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                ranks[i] = 1;
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

            if (ranks[a] < ranks[b]) {
                a += b;
                b = a - b;
                a = a - b;
            }

            parents[b] = a;
            ranks[a] += ranks[b];
        }

        private boolean isConnected() {
            return ranks[find(0)] == ranks.length;
        }
    }

    private void dfs(int node, Map<Integer, Set<Integer>> graph, boolean[] visit) {
        if (visit[node]) return;

        visit[node] = true;

        if (!graph.containsKey(node)) return;

        for (int next : graph.get(node)) {
            dfs(next, graph, visit);
        }
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

    private List<Integer> getPrimes(int n) {
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        for (int i = 2; i <= n; i++) {
            if (!primes[i]) continue;

            for (int j = i * 2; j <= n; j += i) {
                primes[j] = false;
            }
        }

        List<Integer> result = new ArrayList();

        for (int i = 2; i <= n; i++) {
            if (primes[i]) result.add(i);
        }

        return result;
    }

    public boolean try_20250809_tle(int[] nums) {
        Set<Integer> set = new HashSet();
        for (int num : nums) set.add(num);

        if (set.size() == 1) return nums.length == 1 ? true : nums[nums.length - 1] != 1;

        List<Integer> list = new ArrayList(set);
        Collections.sort(list);

        int max = list.get(list.size() - 1);

        Map<Integer, Set<Integer>> graph = new HashMap();

        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i);
            graph.computeIfAbsent(a, k -> new HashSet());
            for (int j = i + 1; j < list.size(); j++) {
                int b = list.get(j);

                if (gcd(a, b) > 1) {
                    graph.get(a).add(b);
                    graph.computeIfAbsent(b, k -> new HashSet()).add(a);
                }
            }
        }

        Set<Integer> visit = new HashSet();

        topdown(list.get(0), graph, visit, list.size());

        return visit.size() == set.size();
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;

        if (a < b) {
            a += b;
            b = a - b;
            a = a - b;
        }

        return gcd(b, a % b);
    }

    private boolean topdown(int node, Map<Integer, Set<Integer>> graph, Set<Integer> visit, int size) {
        if (visit.size() == size) return true;

        if (!visit.add(node)) return false;

        for (int next : graph.get(node)) {
            if (topdown(next, graph, visit, size)) return true;
        }

        return false;
    }
}