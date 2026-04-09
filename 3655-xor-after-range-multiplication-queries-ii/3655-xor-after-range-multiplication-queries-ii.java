class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        return try_official_with_help_Fermat(nums, queries);
    }

    private static final int MOD = 1_000_000_007;

    private int pow(long x, long y) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) == 1) {
                res = (res * x) % MOD;
            }
            x = (x * x) % MOD;
            y >>= 1;
        }
        return (int) res;
    }

    public int try_official_with_help_Fermat(int[] nums, int[][] queries) {
        int n = nums.length;
        int T = (int)Math.sqrt(n);
        List<List<int[]>> groups = new ArrayList(T);

        for (int i = 0; i < T; i++) {
            groups.add(new ArrayList());
        }

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];

            if (k < T) {
                groups.get(k).add(new int[] {l, r, v});
            } else {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int)(((long)nums[i] * v) % MOD);
                }
            }
        }

        long[] diffArray = new long[n + T];

        for (int k = 1; k < T; k++) {
            if (groups.get(k).isEmpty()) continue;

            Arrays.fill(diffArray, 1);

            for (int[] q : groups.get(k)) {
                int l = q[0];
                int r = q[1];
                int v = q[2];

                diffArray[l] = (diffArray[l] * v) % MOD;
                int R = ((r - l) / k + 1) * k + l;
                diffArray[R] = (diffArray[R] * pow(v, MOD - 2)) % MOD;
            }

            for (int i = k; i < n; i++) {
                diffArray[i] = (diffArray[i] * diffArray[i - k]) % MOD;
            }

            for (int i = 0; i < n; i++) {
                nums[i] = (int)(((long)nums[i] * diffArray[i]) % MOD);
            }
        }

        int ans = 0;

        for (int num : nums) {
            ans ^= num;
        }

        return ans;
    }

    public int after_hint_giveup(int[] nums, int[][] queries) {
        int n = nums.length;
        int mod = (int)1e9 + 7;

        Map<Integer, Map<Integer, int[]>> diffArrayMap = new HashMap();

        for (int[] query : queries) {
            int k = query[2];
            int start = query[0] % k;
            int sqrt = (int)Math.sqrt(n);

            if (k <= sqrt) {
                // int[] diffArray = diffArrayMap.computeIfAbsent(k, key1 -> new HashMap()).computeIfAbsent(start -> {
                //     int size = (n + k - 1) / k;

                //     int[] arr = new int[size];

                //     Arrays.fill(arr, 1);

                //     return arr;
                // });

                // for (int i = query[0] / k; i <= query[1] / k; i++) {
                //     diffArray[i] = ((long)diffArray[i] * query[3]) % mod;
                // }
            } else {
                for (int i = query[0]; i <= query[1]; i += k) {
                    nums[i] = (int)(((long)nums[i] * query[3]) % mod);
                }
            }
        }

        for (int k : diffArrayMap.keySet()) {
            // int[] diffArray = diffArrayMap.get(k);

            // for (int i = 0; i < diffArray.length; i++) {
            //     nums[i] = (nums[i * k] * diffArray[i]) % mod;
            // }
        }

        return 0;
    }

    public int mySol_try_segtree_tle(int[] nums, int[][] queries) {
        SegTree st = new SegTree(nums);
        st.init(1, 0, nums.length - 1);

        for (int[] q : queries) {
            st.update(1, 0, nums.length - 1, q[0], q[1], q[0], q[2], q[3]);
        }

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