class Solution {
    public long[] resultArray(int[] nums, int k) {
        return mySol2(nums, k);
    }

    public long[] mySol2(int[] nums, int k) {
        int n = nums.length;
        long[][] prefix = new long[n + 1][k];
        // prefix[0][0] = 1;

        long[][] suffix = new long[n + 1][k];
        // suffix[n][0] = 1;

        for (int i = 0; i < n; i++) {
            int j = n - i - 1;
            for (int x = 0; x < k; x++) {
                prefix[i + 1][(int)(1l * x * nums[i] % k)] += prefix[i][x];
                // suffix[j][x * nums[j] % k] += 1 + suffix[j + 1][x];
            }
            prefix[i + 1][nums[i] % k]++;
        }

        // for (long[] d : prefix) {
        //     System.out.println(Arrays.toString(d) + ", ");
        // }

        // System.out.println("\n");

        // for (long[] d : suffix) {
        //     System.out.println(Arrays.toString(d) + ", ");
        // }

        long[] ans = new long[k];

        for (int i = 0; i <= n; i++) {
            for (int x = 0; x < k; x++) {
                ans[x] += prefix[i][x];
            }
        }

        return ans;
    }

    public long[] mySol_hold(int[] nums, int k) {
        int n = nums.length;
        long[] pMul = new long[n + 1];
        pMul[0] = 1;

        for (int i = 0; i < n; i++) {
            pMul[i + 1] = pMul[i] * nums[i];
        }

        int[] ans = new int[k];

        for (int x = 0; x < k; x++) {
            long mul = 1;
            for (int left = 0, right = 0; right < n; right++) {
                mul *= nums[right];

                // if ()
            }
        }

        return null;
    }
}