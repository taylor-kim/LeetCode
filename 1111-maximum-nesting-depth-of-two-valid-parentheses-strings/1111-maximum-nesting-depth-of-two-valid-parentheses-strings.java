class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        return editorial(seq);
    }

    public int[] editorial(String seq) {
        int n = seq.length();
        int delta = 0;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                ans[i] = delta % 2;
                delta++;
            } else {
                delta--;
                ans[i] = delta % 2;
            }
        }

        return ans;
    }

    public int[] mySol(String seq) {
        int n = seq.length();
        int openA = 0;
        int openB = 0;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int sign = seq.charAt(i) == '(' ? 1 : -1;

            if (sign * openA <= sign * openB) {
                openA += sign;
                ans[i] = 0;
            } else {
                openB += sign;
                ans[i] = 1;
            }
        }

        return ans;
    }
}