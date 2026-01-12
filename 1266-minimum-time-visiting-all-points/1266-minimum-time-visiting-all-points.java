class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        return try_20260112(points);
    }

    public int try_20260112(int[][] points) {
        int ans = 0;

        int prevX = points[0][0];
        int prevY = points[0][1];

        for (int i = 1; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];

            int diffX = x - prevX;
            int diffY = y - prevY;

            ans += Math.max(Math.abs(diffX), Math.abs(diffY));

            prevX = x;
            prevY = y;
        }

        return ans;
    }

    public int mySol(int[][] points) {
        int ans = 0;

        for (int i = 0; i < points.length - 1; i++) {
            int[] cur = points[i];
            int[] next = points[i + 1];

            ans += Math.max(Math.abs(cur[0] - next[0]), Math.abs(cur[1] - next[1]));
        }

        return ans;
    }
}