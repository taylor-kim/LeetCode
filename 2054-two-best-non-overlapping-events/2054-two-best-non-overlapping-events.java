class Solution {
    public int maxTwoEvents(int[][] events) {
        return try_topdown(events);
    }

    public int try_topdown(int[][] events) {
        Arrays.sort(events, (a, b) -> {
            return a[0] - b[0];
        });

        return topdown(events, 0, 0, new Integer[events.length][2]);
    }

    private int topdown(int[][] events, int index, int count, Integer[][] memo) {
        if (index >= events.length || count == 2) return 0;

        if (memo[index][count] != null) return memo[index][count];

        int nextIndex = leftmost(events, index + 1, events[index][1] + 1);

        int include = events[index][2] + topdown(events, nextIndex, count + 1, memo);

        int exclude = topdown(events, index + 1, count, memo);

        return memo[index][count] = Math.max(include, exclude);
    }

    private int leftmost(int[][] events, int lo, int target) {
        int hi = events.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (events[mid][0] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public int editorial_pq(int[][] events) {
        Arrays.sort(events, (a, b) -> {
            return a[0] - b[0];
        });

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        int ans = 0;
        int currentMax = 0;

        for (int[] event : events) {
            while (!pq.isEmpty() && pq.peek()[0] < event[0]) {
                currentMax = Math.max(currentMax, pq.poll()[1]);
            }

            ans = Math.max(ans, currentMax + event[2]);

            pq.add(new int[] {event[1], event[2]});
        }

        return ans;
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