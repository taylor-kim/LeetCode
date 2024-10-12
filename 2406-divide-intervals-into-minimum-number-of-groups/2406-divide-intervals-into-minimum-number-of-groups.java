class Solution {
    public int minGroups(int[][] intervals) {
        return mySol(intervals);
    }

    public int mySol(int[][] intervals) {
        TreeMap<Integer, List<int[]>> map = new TreeMap();

        for (int[] d : intervals) {
            map.computeIfAbsent(d[0], k -> new ArrayList()).add(d);
        }

        int ans = 0;

        while (map.size() > 0) {
            Integer key = map.firstKey();

            while (key != null) {
                int[] d = map.get(key).remove(0);

                if (map.get(key).size() == 0) {
                    map.remove(key);
                }

                key = map.ceilingKey(d[1]);
            }

            ans++;
        }

        return ans;
    }
}