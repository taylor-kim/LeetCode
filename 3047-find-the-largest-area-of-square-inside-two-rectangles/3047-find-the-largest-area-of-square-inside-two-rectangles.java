class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        return mySolBf(bottomLeft, topRight);
    }

    public long mySolBf(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int left1 = bottomLeft[i][0];
            int right1 = topRight[i][0];
            int bot1 = bottomLeft[i][1];
            int top1 = topRight[i][1];

            for (int j = i + 1; j < n; j++) {
                int left2 = bottomLeft[j][0];
                int right2 = topRight[j][0];
                int bot2 = bottomLeft[j][1];
                int top2 = topRight[j][1];

                int xLength = getIntersectLength(left1, right1, left2, right2);
                int yLength = getIntersectLength(bot1, top1, bot2, top2);

                // System.out.println("xLength:%d, yLength:%d".formatted(xLength, yLength));

                int min = Math.min(xLength, yLength);

                ans = Math.max(ans, 1l * min * min);
            }
        }

        return ans;
    }

    private int getIntersectLength(int lo1, int hi1, int lo2, int hi2) {
        int lo = Math.max(lo1, lo2);
        int hi = Math.min(hi1, hi2);
        return Math.max(0, hi - lo);
    }

    private int getIntersectLength_false(int lo1, int hi1, int lo2, int hi2) {
        // [3,4], [1, 5]
        // [1, 5], [3,4]

        if (lo2 <= lo1 && hi2 <= hi1) {
            System.out.println("wtf1 - lo1:%d, hi1:%d, lo2:%d, hi2:%d".formatted(lo1, hi1, lo2, hi2));
            return hi2 - lo1;
        }
        if (lo1 <= lo2 && hi1 <= hi2) {
            System.out.println("wtf2 - lo1:%d, hi1:%d, lo2:%d, hi2:%d".formatted(lo1, hi1, lo2, hi2));
            return hi1 - lo2;
        }
        if (lo1 <= lo2 && hi2 <= hi1) {
            System.out.println("wtf3 - lo1:%d, hi1:%d, lo2:%d, hi2:%d".formatted(lo1, hi1, lo2, hi2));
            return hi2 - lo2;
        }

        System.out.println("wtf4");

        return 0;
    }
}