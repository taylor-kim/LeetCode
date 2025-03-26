class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        return tryLineSweep(firstList, secondList);
    }

    public int[][] tryLineSweep(int[][] list1, int[][] list2) {
        TreeMap<Integer, Integer> map = new TreeMap();

        for (int[] range : list1) {
            map.put(range[0], map.getOrDefault(range[0], 0) + 1);
            map.put(range[1] + 1, map.getOrDefault(range[1] + 1, 0) - 1);
        }

        for (int[] range : list2) {
            map.put(range[0], map.getOrDefault(range[0], 0) + 1);
            map.put(range[1] + 1, map.getOrDefault(range[1] + 1, 0) - 1);
        }

        int pSum = 0;
        int prevTime = 0;
        List<int[]> ans = new ArrayList();

        for (int time : map.keySet()) {
            int count = map.get(time);

            if (pSum == 2) {
                ans.add(new int[] {prevTime, time - 1});
            }

            pSum += count;
            prevTime = time;
        }

        return ans.toArray(int[][]::new);
    }
}