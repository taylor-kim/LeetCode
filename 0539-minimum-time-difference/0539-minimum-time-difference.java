class Solution {
    public int findMinDifference(List<String> timePoints) {
        return mySol(timePoints);
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