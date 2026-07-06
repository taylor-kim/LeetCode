class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        return mySol2(intervals);
    }

    public int mySol2(int[][] intervals) {
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