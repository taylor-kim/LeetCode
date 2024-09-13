class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        return mySol(arr, queries);
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