class Solution {
    public int findMinArrowShots(int[][] points) {
        return others_simple(points);
    }

    public int others_simple(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int ans = 1;

        int shoot = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= shoot) continue;

            shoot = points[i][1];

            ans++;
        }

        return ans;
    }

    public int mySol(int[][] points) {
        int n = points.length;

        if (n == 1) return 1;

        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        int ans = 0;
        int i = 0;

        while (i < n) {
            int[] p = points[i++];
            int left = p[0];
            int right = p[1];

            while (i < n && right >= points[i][0]) {
                right = Math.min(right, points[i][1]);
                i++;
            }

            ans++;
        }

        return ans;
    }
}