class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        return others(n, conflictingPairs);
    }

    // https://www.youtube.com/watch?v=UmshxbwMmrk
    public long others(int n, int[][] conflictingPairs) {
        List<Integer>[] rights = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            rights[i] = new ArrayList();
        }
        
        for (int[] pair : conflictingPairs) {

            rights[Math.max(pair[0], pair[1])].add(Math.min(pair[0], pair[1]));
        }

        int[] lefts = {0, 0};
        long[] bonus = new long[n + 1];

        long ans = 0;

        for (int r = 1; r <= n; r++) {
            for (int left : rights[r]) {
                if (left > lefts[0]) {
                    lefts[1] = lefts[0];
                    lefts[0] = left;
                } else if (left > lefts[1]) {
                    lefts[1] = left;
                }
            }

            ans += r - lefts[0];

            if (lefts[0] > 0) {
                bonus[lefts[0]] += lefts[0] - lefts[1];
            }
        }

        long max = 0;

        for (long b : bonus) {
            max = Math.max(max, b);
        }

        return ans + max;
    }

    public long mySol_fail(int n, int[][] conflictingPairs) {
        UnionFind uf = new UnionFind(n);

        for (int[] conflict : conflictingPairs) {
            uf.union(conflict[0], conflict[1]);
        }

        int min = n + 1;
        int minIndex = -1;

        for (int i = 0; i < conflictingPairs.length; i++) {
            int[] conflict = conflictingPairs[i];

            if (uf.componentSizes[conflict[0]] < min) {
                min = uf.componentSizes[conflict[0]];
                minIndex = i;
            }
        }

        UnionFind uf2 = new UnionFind(n);

        for (int i = 0; i < conflictingPairs.length; i++) {
            if (i == minIndex) continue;

            int[] conflict = conflictingPairs[i];

            uf2.union(conflict[0], conflict[1]);
        }

        Set<Integer> parents = new HashSet();

        for (int i = 1; i <= n; i++) {
            if (uf2.componentSizes[i] == 1) continue;

            parents.add(uf2.find(i));
        }

        return 0l;
    }

    private class UnionFind {
        int[] parents;
        int[] ranks;
        int[] componentSizes;

        private UnionFind(int n) {
            parents = new int[n + 1];
            ranks = new int[n + 1];
            componentSizes = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parents[i] = i;
                componentSizes[i] = 1;
            }
        }

        private int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }
            return parents[a];
        }

        private void union(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) return;

            if (ranks[a] >= ranks[b]) {
                parents[b] = a;
                componentSizes[a] += componentSizes[b];

                if (ranks[a] == ranks[b]) {
                    ranks[a]++;
                }
            } else {
                parents[a] = b;
                componentSizes[b] += componentSizes[a];
            }
        }
    }
}