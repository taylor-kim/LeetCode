class Solution {
    public int maxEvents(int[][] events) {
        return official_pq(events);
    }

    public int official_pq(int[][] events) {
        int n = events.length;
        int max = 0;

        for (int[] event : events) {
            max = Math.max(max, event[1]);
        }

        Queue<Integer> pq = new PriorityQueue();

        Arrays.sort(events, (a, b) -> {
            return a[0] - b[0];
        });

        int ans = 0;

        for (int i = 1, j = 0; i <= max; i++) {
            while (j < n && events[j][0] <= i) {
                pq.add(events[j++][1]);
            }

            while (!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                pq.poll();
                ans++;
            }
        }

        return ans;
    }

    public int mySol2_fail(int[][] events) {
        /**
        1,2,3,4,5,6
        1         -1
        1         -1
        1         -1
        0,1,0,-1,
        0,1,0,-1,
        3,5,5,3,3,3,0
        */

        int n = events.length;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int[] event : events) {
            min = Math.min(min, event[0]);
            max = Math.max(max, event[1]);
        }

        int[] diffArray = new int[max - min + 2];
        
        for (int[] event : events) {
            diffArray[event[0]]++;
            diffArray[event[1] + 1]--;
        }

        int ans = 0;
        int sum = 0;

        return ans;
    }

    public int mySol_fail_tle(int[][] events) {
        Arrays.sort(events, (a, b) -> {
            // return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
            return a[1] != b[1] ? a[1] - b[1] : a[0] - b[0];
        });

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < events.length; i++) {
            min = Math.min(min, events[i][0]);
            max = Math.max(max, events[i][1]);
        }

        Set<Integer> used = new HashSet();

        int ans = 0;

        for (int i = 0; i < events.length; i++) {
            int[] event = events[i];

            int day = event[0];

            while (day <= event[1] && used.contains(day)) {
                day++;
            }

            if (day <= event[1] && used.add(day)) {
                ans++;
            }
        }

        return ans;
    }
}