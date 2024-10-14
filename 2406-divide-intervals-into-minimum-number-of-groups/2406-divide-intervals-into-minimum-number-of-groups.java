class Solution {
    public int minGroups(int[][] intervals) {
        return official_events(intervals);
    }

    public int official_events(int[][] intervals) {
        List<int[]> events = new ArrayList();

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            events.add(new int[] {interval[0], 1});
            events.add(new int[] {interval[1] + 1, -1});
        }

        Collections.sort(events, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        int ans = 0;
        int current = 0;

        for (int[] event : events) {
            current += event[1];

            ans = Math.max(ans, current);
        }

        return ans;
    }

    public int mySol2(int[][] intervals) {
        List<int[]> list = new ArrayList();

        for (int[] d : intervals) list.add(d);

        Collections.sort(list, (a, b) -> {
            return a[0] - b[0];
        });

        int ans = 0;

        while (list.size() > 0) {
            int[] interval = list.remove(0);

            while (interval != null) {
                interval = removeNext(list, interval);
            }

            ans++;
        }

        return ans;
    }

    private int[] removeNext(List<int[]> list, int[] key) {
        if (list.size() == 0) return null;

        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = left + (right - left) / 2;

            int[] found = list.get(mid);

            if (key[1] >= found[0]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int index = Math.min(left, list.size() - 1);

        int[] ret = list.get(index);

        if (key[1] < ret[0]) {
            list.remove(index);
            return ret;
        }

        return null;
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

                key = map.higherKey(d[1]);
            }

            ans++;
        }

        return ans;
    }
}