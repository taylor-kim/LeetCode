class Solution {
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        return official_matrix_multipication(s, t, nums);
    }

    private static final int MOD = (int) 1e9 + 7;
    private static final int L = 26;

    private static class Mat {

        int[][] a = new int[L][L];

        Mat() {}

        Mat(Mat copyFrom) {
            for (int i = 0; i < L; i++) {
                System.arraycopy(copyFrom.a[i], 0, this.a[i], 0, L);
            }
        }

        Mat mul(Mat other) {
            Mat result = new Mat();
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < L; j++) {
                    for (int k = 0; k < L; k++) {
                        result.a[i][j] = (int) ((result.a[i][j] +
                                (long) this.a[i][k] * other.a[k][j]) %
                            MOD);
                    }
                }
            }
            return result;
        }
    }

    /* identity matrix */
    private Mat I() {
        Mat m = new Mat();
        for (int i = 0; i < L; i++) {
            m.a[i][i] = 1;
        }
        return m;
    }

    /* matrix exponentiation by squaring */
    private Mat quickmul(Mat x, int y) {
        Mat ans = I();
        Mat cur = new Mat(x);
        while (y > 0) {
            if ((y & 1) == 1) {
                ans = ans.mul(cur);
            }
            cur = cur.mul(cur);
            y >>= 1;
        }
        return ans;
    }

    public int official_matrix_multipication(String s, int t, List<Integer> nums) {
        Mat T = new Mat();
        for (int i = 0; i < L; i++) {
            for (int j = 1; j <= nums.get(i); j++) {
                T.a[(i + j) % L][i] = 1;
            }
        }

        Mat res = quickmul(T, t);
        int[] f = new int[L];
        for (char ch : s.toCharArray()) {
            f[ch - 'a']++;
        }
        int ans = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                ans = (int) ((ans + (long) res.a[i][j] * f[j]) % MOD);
            }
        }
        return ans;
    }

    public int mySol_TLE(String s, int t, List<Integer> nums) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int mod = (int)1e9 + 7;

        for (int i = 0; i < t; i++) {
            int[] next = new int[26];

            for (int j = 0; j < freq.length; j++) {
                if (freq[j] == 0) continue;

                int limit = nums.get(j);
                
                for (int k = 1; k <= limit; k++) {
                    int index = (j + k) % 26;
                    next[index] = (next[index] + freq[j]) % mod;
                }
            }

            // print(next);

            freq = next;
        }

        int ans = 0;

        for (int count : freq) {
            ans = (ans + count) % mod;
        }

        return ans;
    }

    private void print(int[] next) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < next.length; i++) {
            if (next[i] > 0) {
                sb.append(String.valueOf((char)(i + 'a')).repeat(next[i]));
            }
        }

        System.out.println(sb.toString());
    }
}