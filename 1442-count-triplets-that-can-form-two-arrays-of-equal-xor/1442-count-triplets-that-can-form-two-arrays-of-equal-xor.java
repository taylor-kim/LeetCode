class Solution {
    public int countTriplets(int[] arr) {
        return mySol(arr);
    }

    public int mySol(int[] arr) {
        int n = arr.length;

        int ans = 0;

        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = arr[i] ^ pSum[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = pSum[j] ^ pSum[i];
                for (int k = j; k < n; k++) {
                    int b = pSum[k + 1] ^ pSum[j];

                    if (a == b) ans++;
                }
            }
        }

        return ans;
    }
}