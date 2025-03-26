class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        return others_good_condition(firstList, secondList);
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

    public int[][] others_good_condition(int[][] list1, int[][] list2) {
        List<int[]> ans = new ArrayList();

        int i1 = 0;
        int i2 = 0;

        while (i1 < list1.length && i2 < list2.length) {
            int[] d1 = list1[i1];
            int[] d2 = list2[i2];

            if (d1[0] <= d2[1] && d1[1] >= d2[0]) {
                ans.add(new int[] {Math.max(d1[0], d2[0]), Math.min(d1[1], d2[1])});
            }

            if (d1[1] <= d2[1]) {
                i1++;
            } else {
                i2++;
            }
        }

        return ans.toArray(int[][]::new);
    }

    public int[][] mySol(int[][] list1, int[][] list2) {
        List<int[]> ans = new ArrayList();

        int i1 = 0;
        int i2 = 0;

        while (i1 < list1.length && i2 < list2.length) {
            int[] d1 = list1[i1];
            int[] d2 = list2[i2];

            if (d1[1] < d2[0]) {
                i1++;
            } else if (d2[1] < d1[0]) {
                i2++;
            } else {
                int start = Math.max(d1[0], d2[0]);
                // int end = Math.min(d1[1], d2[1]);
                int end = 0;

                if (d1[1] < d2[1]) {
                    i1++;
                    end = d1[1];
                } else {
                    i2++;
                    end = d2[1];
                }

                ans.add(new int[] {start, end});
            }
        }

        return ans.toArray(int[][]::new);
    }
}