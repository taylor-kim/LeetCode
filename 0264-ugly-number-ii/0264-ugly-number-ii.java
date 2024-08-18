class Solution {
    public int nthUglyNumber(int n) {
        return others(n);
    }

    public int others(int n) {
        int[] arr = new int[n];
        arr[0] = 1;

        int t2 = 0;
        int t3 = 0;
        int t5 = 0;

        for (int i = 1; i < n; i++) {
            arr[i] = Math.min(arr[t2] * 2, Math.min(arr[t3] * 3, arr[t5] * 5));

            if (arr[i] == arr[t2] * 2) t2++;
            if (arr[i] == arr[t3] * 3) t3++;
            if (arr[i] == arr[t5] * 5) t5++;
        }

        return arr[n - 1];
    }
}