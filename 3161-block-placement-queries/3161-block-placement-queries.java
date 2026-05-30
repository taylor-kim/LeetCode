class Solution {
    public List<Boolean> getResults(int[][] queries) {
        return others_segtree(queries);
    }

    public List<Boolean> others_segtree(int[][] queries) {
        int max = Math.min(50000, 3 * queries.length);
        List<Boolean> ans = new ArrayList();
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(max);

        OthersSeg seg = new OthersSeg((max + 1) * 4);
        seg.update(1, 0, max, max, max);

        for (int[] query : queries) {
            int type = query[0];
            int x = query[1];

            if (type == 1) {
                int prev = obstacles.floor(x);
                int next = obstacles.ceiling(x);
                obstacles.add(x);

                seg.update(1, 0, max, x, x - prev);
                seg.update(1, 0, max, next, next - x);
            } else {
                int size = query[2];

                int prev = obstacles.lower(x);

                int maxOfPrev = seg.query(1, 0, max, 0, prev);
                int dist = x - prev;

                ans.add(Math.max(maxOfPrev, dist) >= size);
            }
        }

        return ans;
    }

    public class OthersSeg {
        int[] tree;

        public OthersSeg(int n) {
            tree = new int[n];
        }

        public void update(int node, int left, int right, int index, int value) {
            if (left == right) {
                tree[node] = value;
                return;
            }

            int mid = left + (right - left) / 2;

            if (index <= mid) {
                update(node * 2, left, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, right, index, value);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        public int query(int node, int left, int right, int qLeft, int qRight) {
            if (qLeft > right || qRight < left) return 0;

            if (qLeft <= left && right <= qRight) {
                return tree[node];
            }

            int mid = left + (right - left) / 2;

            int lv = query(node * 2, left, mid, qLeft, qRight);
            int rv = query(node * 2 + 1, mid + 1, right, qLeft, qRight);

            return Math.max(lv, rv);
        }
    }

    public List<Boolean> mySol3_after_hint_fail(int[][] queries) {
        List<Boolean> ans = new ArrayList();
        int max = Math.min(5 * (int)1e4, 3 * queries.length);

        SegTree segTree = new SegTree(max + 1);
        // segTree.build(1, 0, max);

        for (int[] query : queries) {
            int type = query[0];
            int x = query[1];

            if (type == 1) {
                segTree.update(1, 0, max, x);
            }
        }

        return ans;
    }

    public class SegTree {
        int[] values;

        public SegTree(int n) {
            values = new int[n];

            for (int i = 0; i < n; i++) {
                values[i] = n - 1 - i;
            }
        }

        public int query(int node, int left, int right, int qLeft, int qRight) {
            if (left > right) return 0;
            if (qLeft > right || qRight < left) return 0;

            if (qLeft <= left && right <= qRight) {
                return values[node];
            }

            int mid = left + (right - left) / 2;

            int lv = query(node * 2, left, mid, qLeft, qRight);
            int rv = query(node * 2 + 1, mid + 1, right, qLeft, qRight);

            return lv + rv;
        }

        public int update(int node, int left, int right, int x) {
            return 0;
        }
    }

    public List<Boolean> mySol2_fail(int[][] queries) {
        int max = Math.min(5 * (int)1e4, 3 * queries.length);
        Integer[] dp = new Integer[max + 1];

        // for (int i = 0; i <= max; i++) {
        //     dp[i] = i;
        // }

        dp[0] = 0;
        dp[max] = max;

        List<Boolean> ans = new ArrayList();
        List<Integer> blocks = new ArrayList();

        blocks.add(0);
        blocks.add(max);

        for (int[] query : queries) {
            int type = query[0];
            int x = query[1];

            if (type == 1) {
                int index = Collections.binarySearch(blocks, x);

                if (index < 0) {
                    index = -(index + 1);
                }

                blocks.add(index, x);

                int left = blocks.get(index - 1);
                int right = blocks.get(index + 1);

                dp[x] = Math.max(dp[left], x - left);
                dp[right] = Math.max(dp[x], right - x);
            } else {
                int size = query[2];

                if (x < size) {
                    ans.add(false);
                    continue;
                }

                int index = Collections.binarySearch(blocks, x);

                if (index < 0) {
                    index = -(index + 1);
                }

                int left = blocks.get(index - 1);
                int right = blocks.get(Math.min(index + 1, blocks.size() - 1));

                dp[x] = Math.max(dp[left], x - left);
                dp[right] = Math.max(dp[x], right - x);

                int capa = dp[x] != null ? dp[x] : Math.max(x - left, dp[left]);

                ans.add(capa >= size);
            }
        }

        return ans;
    }

    public List<Boolean> mySol_fail(int[][] queries) {
        List<Boolean> ans = new ArrayList();
        List<Integer> blocks = new ArrayList();

        blocks.add(0);
        blocks.add(Integer.MAX_VALUE);

        Map<Integer, Integer> capas = new HashMap();
        capas.put(0, Integer.MAX_VALUE);

        for (int[] query : queries) {
            int type = query[0];
            int x = query[1];
            if (type == 1) {
                blocks.add(x);
                int index = Collections.binarySearch(blocks, x);
                int left = blocks.get(index - 1);
                int right = blocks.get(index + 1);

                capas.put(left, x - left);
                capas.put(x, right - x);
            } else {
                // System.out.println(blocks);

                int size = query[2];

                if (x < size) {
                    ans.add(false);
                    continue;
                } else if (blocks.isEmpty()) {
                    ans.add(true);
                    continue;
                }

                boolean found = false;
                int left = 0;
                for (int right : blocks) {
                    if (right > x) break;

                    if (right - left >= size) {
                        found = true;
                        break;
                    }

                    left = right;
                }

                if (left < x) {
                    if (x - left >= size) {
                        found = true;
                    }
                }

                ans.add(found);
            }
        }

        return ans;
    }
}