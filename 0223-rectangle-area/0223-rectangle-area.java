class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        return try_20260201(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
    }

    public int try_20260201(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int totalArea = Math.abs(ax2 - ax1) * Math.abs(ay2 - ay1);
        totalArea += Math.abs(bx2 - bx1) * Math.abs(by2 - by1);

        int width = Math.max(Math.min(ax2, bx2) - Math.max(ax1, bx1), 0);
        int hight = Math.max(Math.min(ay2, by2) - Math.max(ay1, by1), 0);

        return totalArea - width * hight;
    }
}