class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        return others_good(intervals);
    }

    public int others_good(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : b[1] - a[1];
        });

        int ans = 0;
        int lastEnd = 0;

        for (int[] interval : intervals) {
            if (lastEnd < interval[1]) {
                lastEnd = interval[1];
                ans++;
            }
        }

        return ans;
    }

    public int bf(int[][] intervals) {
        int ans = 0;
        int n = intervals.length;

        for (int i = 0; i < n; i++) {
            int a = intervals[i][0];
            int b = intervals[i][1];
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                int c = intervals[j][0];
                int d = intervals[j][1];
                
                if (c <= a && b <= d) {
                    ans++;
                    break;
                }
            }
        }

        return n - ans;
    }

    public int mySol(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap();

        for (int[] interval : intervals) {
            map.put(interval[0], Math.max(map.getOrDefault(interval[0], 0), interval[1]));
        }

        Integer a = map.firstKey();

        while (a != null) {
            int b = map.get(a);
            Integer c = map.higherKey(a);

            while (c != null) {
                int d = map.get(c);

                if (d <= b) {
                    map.remove(c);
                }

                c = map.higherKey(c);
            }

            a = map.higherKey(a);
        }

        return map.size();
    }
}