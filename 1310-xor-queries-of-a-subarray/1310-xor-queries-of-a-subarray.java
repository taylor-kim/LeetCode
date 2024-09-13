class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        return official_inplace(arr, queries);
    }

    public int[] official_inplace(int[] arr, int[][] queries) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] ^= arr[i - 1];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];

            if (q[0] > 0) {
                ans[i] = arr[q[0] - 1] ^ arr[q[1]];
            } else {
                ans[i] = arr[q[1]];
            }
        }

        return ans;
    }

    public int[] mySol(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] pSum = new int[n + 1];
        int[] ans = new int[queries.length];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] ^ arr[i];
        }

        for (int i = 0; i < queries.length; i++) {
            ans[i] = pSum[queries[i][1] + 1] ^ pSum[queries[i][0]];
        }

        return ans;
    }
}