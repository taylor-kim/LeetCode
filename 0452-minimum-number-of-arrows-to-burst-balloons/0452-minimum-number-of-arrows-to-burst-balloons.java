class Solution {
    public int findMinArrowShots(int[][] points) {
        return mySol(points);
    }

    public int mySol(int[][] points) {
        int n = points.length;

        if (n == 1) return 1;

        long[][] lp = new long[n][2];

        for (int i = 0; i < n; i++) {
            int[] p = points[i];
            lp[i][0] = p[0];
            lp[i][1] = p[1];
        }

        Arrays.sort(lp, (a, b) -> Long.compare(a[0], b[0]));

        int ans = 0;
        int i = 0;

        while (i < n) {
            long[] p = lp[i++];
            long left = p[0];
            long right = p[1];

            while (i < n && right >= lp[i][0]) {
                right = Math.min(right, lp[i][1]);
                i++;
            }

            ans++;
        }

        return ans;
    }
}