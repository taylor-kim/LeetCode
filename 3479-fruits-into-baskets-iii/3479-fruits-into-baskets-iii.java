class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        return official(fruits, baskets);
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

                if (segmentTree.query(1, 0, n - 1, 0, mid) >= fruits[i]) {
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
            if (left == right) {
                tree[node] = val;
                return;
            }

            int mid = left + (right - left) / 2;

            if (pos <= mid) {
                update(node * 2, left, mid, pos, val);
            } else {
                update(node * 2 + 1, mid + 1, right, pos, val);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }
}