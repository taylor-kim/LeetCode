class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        return mySol2(classes, extraStudents);
    }

    public double mySol2(int[][] classes, int extra) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            double deltaA = ((a[0] + 1) / (double)(a[1] + 1)) - (a[0] / (double)a[1]);
            double deltaB = ((b[0] + 1) / (double)(b[1] + 1)) - (b[0] / (double)b[1]);
            
            return deltaB > deltaA ? 1 : -1;
        });

        for (int[] each : classes) pq.add(each);

        double ans = 0.0;

        while (extra-- > 0) {
            int[] data = pq.poll();

            if (data[0] == data[1]) {
                return classes.length / (double)extra;
            }

            data[0]++;
            data[1]++;
            pq.add(data);
        }

        // System.out.println(String.format("====== size:%d, ans:%f", pq.size(), ans));

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            // System.out.println(Arrays.toString(data));
            ans += data[0] / (double)data[1];
        }

        return ans / classes.length;
    }

    public double mySol_tle(int[][] classes, int extra) {
        // Arrays.sort(classes, (a, b) -> {
        //     return a[0] / (double)a[1] - b[0] / (double)b[1];
        // });

        Arrays.sort(classes, Comparator.comparingDouble(d -> d[0] / (double)d[1]));

        for (int[] clazz : classes) {
            System.out.print(String.format("%s, ", Arrays.toString(clazz)));
        }

        return topdown(classes, 0, extra, new Double[classes.length][extra + 1]) / classes.length;
    }

    public double topdown(int[][] classes, int index, int extra, Double[][] memo) {
        if (index >= classes.length) return 0d;

        if (memo[index][extra] != null) {
            return memo[index][extra];
        }

        int[] clazz = classes[index];

        double a = (double)clazz[0];
        double b = (double)clazz[1];
        double ans = 0;

        for (int i = 0; i < extra; i++) {
            ans = Math.max(ans, (a + i + 1) / (b + i + 1) + topdown(classes, index + 1, extra - i - 1, memo));
        }

        ans = Math.max(ans, a / b + topdown(classes, index + 1, extra, memo));

        return memo[index][extra] = ans;
    }
}