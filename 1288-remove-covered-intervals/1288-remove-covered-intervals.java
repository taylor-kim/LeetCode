class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        return mySol(intervals);
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