class Solution {
    public double largestTriangleArea(int[][] points) {
        return bf(points);
    }

    public double bf(int[][] points) {
        double ans = 0.0;

        int n = points.length;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int[] p1 = points[i];
                    int[] p2 = points[j];
                    int[] p3 = points[k];

                    // int left = Math.min(p1[0], Math.min(p2[0], p3[0]));
                    // int right = Math.max(p1[0], Math.max(p2[0], p3[0]));

                    // int top = Math.max(p1[1], Math.max(p2[1], p3[1]));
                    // int bottom = Math.min(p1[1], Math.min(p2[1], p3[1]));

                    // ans = Math.max(ans, 1.0d * (right - left) * (top - bottom) / 2);

                    double area = 1.0d * Math.abs(p1[0] * (p2[1] - p3[1]) + p2[0] * (p3[1] - p1[1]) + p3[0] * (p1[1] - p2[1])) / 2;

                    ans = Math.max(ans, area);
                }
            }
        }

        return ans;
    }
}