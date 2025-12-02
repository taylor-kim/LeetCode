class Solution {
    public int countTrapezoids(int[][] points) {
        return mySol2(points);
    }

    public int mySol2(int[][] points) {
        Map<Integer, Integer> yBaseMap = new HashMap();

        for (int[] point : points) {
            yBaseMap.put(point[1], yBaseMap.getOrDefault(point[1], 0) + 1);
        }

        int mod = (int)1e9 + 7;
        int ans = 0;

        List<Integer> list = new ArrayList();

        for (int num : yBaseMap.values()) {
            // list.add((int)(ncr(num, 2) % mod));
            list.add((int)((1l * num * (num - 1) / 2) % mod));
        }

        // a, b, c, d
        // a*b + a*c + a*d + b*c + b*d + c*d
        // a * (b + c + d)

        int[] pSum = new int[list.size() + 1];

        for (int i = 0; i < list.size(); i++) {
            pSum[i + 1] = (pSum[i] + list.get(i)) % mod;
        }

        for (int i = 0; i < list.size(); i++) {
            int multi = (int)((1l * list.get(i) * (pSum[list.size()] - pSum[i + 1])) % mod);
            ans = (ans + multi) % mod;

            // for (int j = i + 1; j < list.size(); j++) {
            //     int multi = (int)((1l * list.get(i) * list.get(j)) % mod);
            //     ans = (ans + multi) % mod;
            // }
        }

        return ans;
    }

    private long ncr(long n, long r) {
        if (n < r) return 0;
        if (n == r) return 1;

        return factorial(n) / factorial(n - r) / factorial(r);
    }

    private long factorial(long n) {
        if (n == 1) return 1;

        int mod = (int)1e9 + 7;

        return (n * factorial(n - 1)) % mod;
    }

    public int mySol_hold(int[][] points) {
        return topdown(points, 0, 1, 2, 3);
    }

    private Set<String> visit = new HashSet();

    public int topdown(int[][] points, int a, int b, int c, int d) {
        if (d >= points.length) return 0;

        String key = "%d_%d_%d_%d".formatted(a, b, c, d);

        System.out.println(key);

        if (!visit.add(key)) {
            throw new RuntimeException("wtf");
        }

        int ans = 0;

        if (isHorizontalTrapezoid(points, a, b, c, d)) {
            ans++;
        }

        int mod = (int)1e9 + 7;

        ans = (ans + topdown(points, a, b, c, d + 1)) % mod;
        ans = (ans + topdown(points, a, b, c + 1, d + 1)) % mod;
        ans = (ans + topdown(points, a, b + 1, c + 1, d + 1)) % mod;
        ans = (ans + topdown(points, a + 1, b + 1, c + 1, d + 1)) % mod;

        return 0;
    }

    private boolean isHorizontalTrapezoid(int[][] points, int a, int b, int c, int d) {
        return false;
    }
}