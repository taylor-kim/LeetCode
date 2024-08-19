class Solution {
    public int minSteps(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        return mySol(n, 1, 0);
    }

    public int mySol(int n, int count, int paste) {
        if (n == count) {
            return 0;
        }

        if (n < count) return -1;

        // System.out.println(String.format("count:%d, paste:%d", count, paste));

        int min = Integer.MAX_VALUE;

        if (paste > 0) {
            int justPaste = mySol(n, count + paste, paste);

            if (justPaste >= 0) {
                min = Math.min(min, justPaste + 1);
            }
        }

        int cAndP = mySol(n, count * 2, count);

        if (cAndP >= 0) {
            min = Math.min(min, cAndP + 2);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}