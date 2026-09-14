class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return editorial(rec1, rec2);
    }

    public boolean editorial(int[] rec1, int[] rec2) {
        return Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])
                && Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]);
    }

    public boolean mySol_fail(int[] rec1, int[] rec2) {
        return isOverlap(rec1[0], rec1[2], rec2[0], rec2[2])
            && isOverlap(rec1[1], rec1[3], rec2[1], rec2[3]);
    }

    private boolean isOverlap(int a1, int b1, int a2, int b2) {
        if (a1 > a2) return isOverlap(a2, b2, a1, b1);

        return b2 > a1;
    }
}