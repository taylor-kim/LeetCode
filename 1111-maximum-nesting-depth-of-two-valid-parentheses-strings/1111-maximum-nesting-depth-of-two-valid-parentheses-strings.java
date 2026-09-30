class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        return mySol(seq);
    }

    public int[] mySol(String seq) {
        int n = seq.length();
        int openA = 0;
        int openB = 0;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                if (openA <= openB) {
                    openA++;
                    ans[i] = 0;
                } else {
                    openB++;
                    ans[i] = 1;
                }
            } else {
                if (openA >= openB) {
                    openA--;
                    ans[i] = 0;
                } else {
                    openB--;
                    ans[i] = 1;
                }
            }
        }

        return ans;
    }
}