class Solution {
    public int maxTwoEvents(int[][] events) {
        return mySol(events);
    }

    public int mySol(int[][] events) {
        TreeMap<Integer, Integer> dp = new TreeMap();
        dp.put(0, 0);

        int max = 0;

        Arrays.sort(events, (a, b) -> {
            return a[1] - b[1];
        });

        for (int[] event : events) {
            int prevMaxKey = dp.lowerKey(event[0]);
            int prevMaxValue = dp.get(prevMaxKey);

            max = Math.max(max, prevMaxValue + event[2]);

            dp.put(event[1], Math.max(event[2], dp.get(dp.floorKey(event[1]))));
        }

        return max;
    }
}