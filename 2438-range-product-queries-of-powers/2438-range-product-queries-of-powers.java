class Solution {
    public int[] productQueries(int n, int[][] queries) {
        return mySol(n, queries);
    }

    public int[] mySol(int n, int[][] queries) {
        List<Integer> powers = getPowers(n);
        SegmentTree st = new SegmentTree(powers);
        st.init(1, 0, powers.size() - 1);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            ans[i] = st.query(1, 0, powers.size() - 1, query[0], query[1]);
        }

        return ans;
    }

    public List<Integer> getPowers(int n) {
        List<Integer> list = new LinkedList();

        for (int i = 0; i < 32; i++) {
            int bit = 1 << i;

            if ((n & bit) != 0) {
                list.add(bit);
            }
        }

        return list;
    }

    public class SegmentTree {
        private int mod = (int)1e9 + 7;
        private List<Integer> powers;

        private int[] data;

        public SegmentTree(List<Integer> powers) {
            this.data = new int[powers.size() * 4 + 1];
            this.powers = powers;
        }

        public int init(int node, int left, int right) {
            if (left == right) {
                return data[node] = powers.get(left);
            }

            if (left > right) return 1;

            int mid = left + (right - left) / 2;

            long lv = (long)init(node * 2, left, mid);
            long rv = (long)init(node * 2 + 1, mid + 1, right);

            return this.data[node] = (int)((lv * rv) % mod);
        }

        public int query(int node, int left, int right, int qLeft, int qRight) {
            if (qRight < left || right < qLeft) return 1;

            if (qLeft <= left && right <= qRight) {
                return data[node];
            }

            int mid = left + (right - left) / 2;

            long lv = (long)query(node * 2, left, mid, qLeft, qRight);
            long rv = (long)query(node * 2 + 1, mid + 1, right, qLeft, qRight);

            return (int)((lv * rv) % mod);
        }
    }
}