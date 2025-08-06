class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        return official_sqaure_root_decomposition(fruits, baskets);
    }

    public int official_sqaure_root_decomposition(int[] fruits, int[] baskets) {
        int n = fruits.length;
        int m = (int)Math.sqrt(n);
        int section = (n + m - 1) / m;

        int[] sections = new int[section];

        for (int i = 0; i < n; i++) {
            sections[i / m] = Math.max(sections[i / m], baskets[i]);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int s = 0; s < section; s++) {
                if (sections[s] < fruits[i]) continue;

                int max = 0;

                for (int j = 0; j < m; j++) {
                    int index = s * m + j;

                    if (index < n) {
                        if (baskets[index] >= fruits[i] && count == 1) {
                            baskets[index] = 0;
                            count = 0;
                        }

                        max = Math.max(max, baskets[index]);
                    }
                }

                sections[s] = max;

                if (count == 0) break;
            }

            ans += count;
        }

        return ans;
    }

    public int official(int[] fruits, int[] baskets) {
        int n = baskets.length;

        SegmentTree segmentTree = new SegmentTree(n * 4 + 1);

        segmentTree.build(1, 0, n - 1, baskets);

        int ans = 0;

        for (int i = 0; i < fruits.length; i++) {
            int lo = 0;
            int hi = n - 1;
            int index = -1;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;

                if (segmentTree.query(1, 0, n - 1, lo, mid) >= fruits[i]) {
                    index = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            if (index != -1) {
                segmentTree.update(1, 0, n - 1, index, Integer.MIN_VALUE);
            } else {
                ans++;
            }
        }

        return ans;
    }

    private class SegmentTree {
        int[] tree;

        public SegmentTree(int n) {
            this.tree = new int[n];
            Arrays.fill(tree, Integer.MIN_VALUE);
        }

        private int build(int node, int left, int right, int[] array) {
            if (left == right) {
                return tree[node] = array[left];
            }

            int mid = left + (right - left) / 2;

            return tree[node] = Math.max(
                build(node * 2, left , mid, array)
                , build(node * 2 + 1, mid + 1, right, array)
            );
        }

        private int query(int node, int left, int right, int qLeft, int qRight) {
            if (qLeft > right || qRight < left) {
                return Integer.MIN_VALUE;
            }

            if (qLeft <= left && right <= qRight) {
                return tree[node];
            }

            int mid = left + (right - left) / 2;

            return Math.max(
                query(node * 2, left, mid, qLeft, qRight)
                , query(node * 2 + 1, mid + 1, right, qLeft, qRight)
            );
        }

        private void update(int node, int left, int right, int pos, int val) {
            if (pos < left || pos > right) return;
            
            if (left == right) {
                tree[node] = val;
                return;
            }

            int mid = left + (right - left) / 2;

            update(node * 2, left, mid, pos, val);
            update(node * 2 + 1, mid + 1, right, pos, val);

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);

            // int mid = left + (right - left) / 2;

            // if (pos <= mid) {
            //     update(node * 2, left, mid, pos, val);
            // } else {
            //     update(node * 2 + 1, mid + 1, right, pos, val);
            // }

            // tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }
}