class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        return editorial(n, restrictions);
    }

    public int editorial(int n, int[][] restrictions) {
        List<int[]> list = new ArrayList();

        for (int[] r : restrictions) {
            list.add(new int[] {r[0], r[1]});
        }

        list.add(new int[] {1, 0});

        Collections.sort(list, (a, b) -> {
            return a[0] - b[0];
        });

        if (list.get(list.size() - 1)[0] != n) {
            list.add(new int[] {n, n - 1});
        }

        for (int i = 1; i < list.size(); i++) {
            int dist = list.get(i)[0] - list.get(i - 1)[0];
            list.get(i)[1] = Math.min(list.get(i)[1], list.get(i - 1)[1] + dist);
        }

        for (int i = list.size() - 1; i >= 1; i--) {
            int dist = list.get(i)[0] - list.get(i - 1)[0];
            list.get(i - 1)[1] = Math.min(list.get(i - 1)[1], list.get(i)[1] + dist);
        }

        int ans = 0;

        for (int i = 0; i < list.size() - 1; i++) {
            int l = list.get(i)[0];
            int h1 = list.get(i)[1];
            int r = list.get(i + 1)[0];
            int h2 = list.get(i + 1)[1];

            int best = (r - l + h1 + h2) / 2;

            ans = Math.max(ans, best);
        }

        return ans;
    }

    public int try_20260718_mle(int n, int[][] restrictions) {
        Map<Integer, Integer> map = new HashMap();
        // int[] limits = new int[restrictions.length];
        int index = 0;

        for (int[] r : restrictions) {
            map.put(r[0], r[1]);
            // limits[index++] = r[0];
        }

        // Arrays.sort(limits);

        int[] lToR = new int[n + 1];
        int[] rToL = new int[n + 2];
        rToL[n + 1] = n;

        for (int i = 2; i < lToR.length; i++) {
            int h = Math.min(i - 1, lToR[i - 1] + 1);
            int r = map.getOrDefault(i, n);
            int adj = Math.min(h, r);
            lToR[i] = adj;
        }

        for (int i = n; i >= 1; i--) {
            int h = Math.min(i - 1, rToL[i + 1] + 1);
            int r = map.getOrDefault(i, n);
            int adj = Math.min(h, r);
            rToL[i] = adj;
        }

        // System.out.println(Arrays.toString(lToR));
        // System.out.println(Arrays.toString(rToL));

        int max = 0;

        for (int i = 1; i <= n; i++) {
            max = Math.max(max, Math.min(lToR[i], rToL[i]));
        }
        
        return max;
    }

    public int mySol4(int n, int[][] restrictions) {
        // Arrays.sort(restrictions, (a, b) -> {
        //     return a[0] - b[0];
        // });

        TreeMap<Integer, Integer> rest = new TreeMap();

        for (int[] each : restrictions) {
            rest.put(each[0], each[1]);
        }

        int max = (int)1e9;

        rest.put(1, 0);
        rest.put(n + 1, max);

        return 0;
    }

    public int mySol3_ii(int n, int[][] restrictions) {
        TreeMap<Integer, Integer> rest = new TreeMap();

        for (int[] each : restrictions) {
            rest.put(each[0], each[1]);
        }

        int max = (int)1e9;

        rest.put(1, 0);
        rest.put(n + 1, max);

        int[] dp = new int[n + 2];
        Arrays.fill(dp, max);
        dp[1] = 0;

        for (int left = 2; left <= n; left++) {
            int right = n - left + 2;

            int lToR = Math.min(dp[left - 1] + 1, rest.getOrDefault(left, max));
            int rToL = Math.min(dp[right + 1] + 1, rest.getOrDefault(right, max));

            dp[left] = Math.min(dp[left], lToR);
            dp[right] = Math.min(dp[right], rToL);
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public int mySol3_mle(int n, int[][] restrictions) {
        TreeMap<Integer, Integer> rest = new TreeMap();

        for (int[] each : restrictions) {
            rest.put(each[0], each[1]);
        }

        int max = (int)1e9;

        rest.put(1, 0);
        rest.put(n + 1, max);

        int[] lToR = new int[n + 1];
        int[] rToL = new int[n + 2];
        rToL[n + 1] = max;

        for (int left = 2; left <= n; left++) {
            int right = n - left + 2;

            lToR[left] = Math.min(lToR[left - 1] + 1, rest.getOrDefault(left, max));

            rToL[right] = Math.min(rToL[right + 1] + 1, rest.getOrDefault(right, max));
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, Math.min(lToR[i], rToL[i]));
        }

        return ans;
    }

    public int mySol2_fail(int n, int[][] restrictions) {
        TreeMap<Integer, Integer> rest = new TreeMap();

        for (int[] each : restrictions) {
            rest.put(each[0], each[1]);
        }

        int max = Integer.MAX_VALUE - 10;

        rest.put(n + 1, max);

        int ans = 0;
        int prev = 0;

        System.out.println(rest);

        for (int i = 2; i <= n; i++) {
            int h = Math.min(i - 1, prev + 1);
            int r = rest.getOrDefault(i, max);

            int actual = Math.min(h, r);

            int nextKey = rest.higherKey(i);

            int distance = nextKey - i;

            int nextR = rest.get(nextKey);

            // System.out.println("num:%d, actual:%d, nextR:%d".formatted(i, actual, nextR));

            if (actual > nextR) {
                actual = Math.min(actual, nextR + distance);
            }

            ans = Math.max(ans, actual);

            prev = actual;
        }

        return ans;
    }

    public int mySol_fail(int n, int[][] restrictions) {
        Map<Integer, Integer> rest = new HashMap();

        for (int[] each : restrictions) {
            rest.put(each[0], each[1]);
        }

        return topdown(n, rest, 2, 0);
    }

    public int topdown(int n, Map<Integer, Integer> rest, int num, int prevH) {
        if (prevH < 0) return -1;
        if (num > n) return prevH;

        int r = rest.getOrDefault(num, Integer.MAX_VALUE - 10);

        if (prevH > r + 1 || prevH < r - 1) return -1;

        int smaller = prevH - 1;
        int taller = Math.min(prevH + 1, r);

        int a = topdown(n, rest, num + 1, smaller);
        int b = topdown(n, rest, num + 1, prevH);
        int c = topdown(n, rest, num + 1, taller);

        return Math.max(a, Math.max(b, c));
    }
}