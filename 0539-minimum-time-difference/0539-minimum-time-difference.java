class Solution {
    public int findMinDifference(List<String> timePoints) {
        return official_bucket(timePoints);
    }

    public int official_bucket(List<String> timePoints) {
        boolean[] mins = new boolean[24 * 60];

        int first = Integer.MAX_VALUE;
        int last = -1;

        for (String hhmm : timePoints) {
            int min = toMin(hhmm);

            if (mins[min]) return 0;

            first = Math.min(first, min);
            last = Math.max(last, min);

            mins[min] = true;
        }

        int prevMin = first;

        int ans = Integer.MAX_VALUE;

        for (int min = first + 1; min <= last; min++) {
            if (!mins[min]) continue;

            ans = Math.min(ans, min - prevMin);

            prevMin = min;
        }

        return Math.min(ans, first + 1440 - last);
    }

    public int mySol(List<String> timePoints) {
        int ans = Integer.MAX_VALUE;

        Set<Integer> set = new HashSet();

        for (String hhmm : timePoints) {
            if (!set.add(toMin(hhmm))) return 0;
        }

        List<Integer> list = new ArrayList(set);

        Collections.sort(list);

        for (int i = 1; i < list.size(); i++) {
            ans = Math.min(ans, list.get(i) - list.get(i - 1));
        }

        ans = Math.min(list.get(0) + 1440 - list.get(list.size() - 1), ans);

        return ans;
    }

    private int toMin(String time) {
        String[] hhmm = time.split(":");
        
        return Integer.parseInt(hhmm[0]) * 60 + Integer.parseInt(hhmm[1]);
    }
}