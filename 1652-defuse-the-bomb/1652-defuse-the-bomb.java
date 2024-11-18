class Solution {
    public int[] decrypt(int[] code, int k) {
        return mySol(code, k);
    }

    public int[] mySol(int[] code, int k) {
        int n = code.length;
        int[] pSum = new int[n];
        pSum[0] = code[0];

        for (int i = 1; i < n; i++) {
            pSum[i] = pSum[i - 1] + code[i];
        }

        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = 0;
            if (k > 0) {
                start = (i + 1) % n;
                end = (start + k - 1) % n;
            } else if (k < 0) {
                end = (i - 1 + n) % n;
                start = (end + k + n + 1) % n;
            } else {
                code[i] = 0;
                continue;
            }

            // System.out.println(String.format("start:%d, end:%d", start, end));

            if (start <= end) {
                code[i] = pSum[end] - (start == 0 ? 0 : pSum[start - 1]);
            } else {
                code[i] = pSum[n - 1] - pSum[start - 1] + pSum[end];
            }
        }

        return code;
    }
}