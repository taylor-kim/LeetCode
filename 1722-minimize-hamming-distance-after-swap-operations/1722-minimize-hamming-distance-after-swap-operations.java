class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        return mySol(source, target, allowedSwaps);
    }

    public int mySol(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        UnionFind uf = new UnionFind(n);

        for (int[] allowedSwap : allowedSwaps) {
            uf.merge(allowedSwap[0], allowedSwap[1]);
        }

        Map<Integer, List<Integer>> groups = new HashMap();

        int ans = 0;

        for (int i = 0; i < n; i++) {
            groups.computeIfAbsent(uf.find(i), k -> new ArrayList()).add(i);
            // if (uf.find(i) == i) {
            //     if (source[i] != target[i]) ans++;
            // } else {
            //     groups.computeIfAbsent(uf.find(i), k -> new ArrayList()).add(i);
            // }
        }

        // System.out.println("ans:%d, groups:%s".formatted(ans, groups));

        for (List<Integer> indices : groups.values()) {
            if (indices.size() == 1) {
                if (source[indices.get(0)] != target[indices.get(0)]) ans++;
                continue;
            }

            Map<Integer, Integer> counter = new HashMap();

            for (int i : indices) {
                counter.put(source[i], counter.getOrDefault(source[i], 0) + 1);
                counter.put(target[i], counter.getOrDefault(target[i], 0) - 1);
            }

            // System.out.println(counter);

            int missMatched = 0;

            for (int count : counter.values()) {
                if (count != 0) {
                    missMatched += Math.abs(count);
                }
            }

            ans += missMatched / 2;
        }

        return ans;
    }

    public class UnionFind {
        int[] parents;
        int[] orders;

        public UnionFind(int n) {
            parents = new int[n];
            orders = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                orders[i] = 0;
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

            if (orders[a] > orders[b]) {
                parents[b] = a;
                orders[a] += orders[b];
            } else if (orders[a] < orders[b]) {
                parents[a] = b;
                orders[b] += orders[a];
            } else {
                parents[a] = b;
                orders[a]++;
            }
        }
    }
}