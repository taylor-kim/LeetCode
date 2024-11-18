class Solution {
    public int[] decrypt(int[] code, int k) {
        return official_slidingwindow(code, k);
    }

    public int[] official_slidingwindow(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];

        if (k == 0) return ans;

        int start = 1;
        int end = k;

        if (k < 0) {
            end = n - 1;
            start = end + k + 1;
        }

        int sum = 0;

        for (int i = start; i <= end; i++) sum += code[i];

        for (int i = 0; i < n; i++) {
            ans[i] = sum;
            sum -= code[start++ % n];
            sum += code[++end % n];
        }

        return ans;
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