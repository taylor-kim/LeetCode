class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        return mySol_try_segtree(nums, queries);
    }

    public int mySol_bf(int[] nums, int[][] queries) {
        int mod = (int)1e9 + 7;

        for (int[] q : queries) {
            int index = q[0];

            while (index <= q[1]) {
                nums[index] = (int)((1l * nums[index] * q[3]) % mod);

                index += q[2];
            }
        }

        int ans = 0;

        for (int num : nums) ans ^= num;

        return ans;
    }

    public int mySol_try_segtree(int[] nums, int[][] queries) {
        SegTree st = new SegTree(nums);
        st.init(1, 0, nums.length - 1);

        for (int[] q : queries) {
            st.update(1, 0, nums.length - 1, q[0], q[1], q[0], q[2], q[3]);
        }

        // System.out.println(Arrays.toString(st.data));

        return st.query(1, 0, nums.length - 1, 0, nums.length - 1);
    }

    class SegTree {
        private int[] data;
        private int[] nums;
        private long mod = (long)1e9 + 7;
        
        SegTree(int[] nums) {
            this.data = new int[nums.length * 4 + 1];
            this.nums = nums;
        }

        int init(int node, int left, int right) {
            if (left == right) {
                return data[node] = nums[left];
            }

            if (left > right) return -1;

            int mid = left + (right - left) / 2;

            int lv = init(node * 2, left, mid);
            int rv = init(node * 2 + 1, mid + 1, right);

            // if (lv >= 0) {
            //     this.data[node] ^= lv;
            // }

            // if (rv >= 0) {
            //     this.data[node] ^= rv;
            // }

            return this.data[node];
        }

        private int query(int node, int left, int right, int qLeft, int qRight) {
            if (qRight < left || right < qLeft) return -1;

            if (left == right) {
                return data[node];
            } else {
                int mid = left + (right - left) / 2;

                int lv = query(node * 2, left, mid, qLeft, qRight);
                int rv = query(node * 2 + 1, mid + 1, right, qLeft, qRight);

                int ans = 0;

                if (lv >= 0) ans ^= lv;
                if (rv >= 0) ans ^= rv;

                return ans;
            }
        }

        int update(int node, int left, int right, int qLeft, int qRight, int index, int k, int v) {
            if (qRight < left || right < qLeft) return -1;

            if (left == right) {
                if (left == index || (left - index) % k == 0) {
                    return data[node] = (int)((1l * v * data[node]) % mod);
                } else {
                    return data[node];
                }
            } else {
                int mid = left + (right - left) / 2;

                int lv = update(node * 2, left, mid, qLeft, qRight, index, k, v);
                int rv = update(node * 2 + 1, mid + 1, right, qLeft, qRight, index, k, v);

                // if (lv >= 0) {
                //     data[node] ^= lv;
                // }

                // if (rv >= 0) {
                //     data[node] ^= rv;
                // }

                return data[node];
            }
        }
    }
}