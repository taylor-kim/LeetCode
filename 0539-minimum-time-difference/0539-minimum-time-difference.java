class Solution {
    public int findMinDifference(List<String> timePoints) {
        return official_bucket(timePoints);
    }

    public int official_bucket(List<String> timePoints) {
        boolean[] mins = new boolean[24 * 60];

        for (String hhmm : timePoints) {
            int min = toMin(hhmm);

            if (mins[min]) return 0;

            mins[min] = true;
        }

        int prevMin = -1;
        int firstMin = -1;
        int lastMin = -1;

        int ans = Integer.MAX_VALUE;

        for (int min = 0; min < 24 * 60; min++) {
            if (!mins[min]) continue;

            if (firstMin == -1) {
                firstMin = min;
            }

            if (prevMin != -1) {
                ans = Math.min(ans, min - prevMin);
            }

            prevMin = min;
            lastMin = min;
        }

        return Math.min(ans, firstMin + 1440 - lastMin);
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