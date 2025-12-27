class Solution {
    public int mostBooked(int n, int[][] meetings) {
        return mySol(n, meetings);
    }

    public int mySol(int n, int[][] meetings) {
        Queue<int[]> remains = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        for (int[] meeting : meetings) {
            remains.add(meeting);
        }

        Queue<Integer> availables = new PriorityQueue();
        
        for (int i = 0; i < n; i++) {
            availables.add(i);
        }

        Queue<long[]> progresses = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]);
        });

        int[] counter = new int[n];
        long time = 0;

        while (!remains.isEmpty()) {
            int[] meeting = remains.poll();

            // time = Math.max(time, meeting[0]);
            time = meeting[0];

            while (!progresses.isEmpty() && progresses.peek()[0] <= time) {
                int room = (int)progresses.poll()[1];

                availables.add(room);
            }

            if (availables.isEmpty()) {
                time = progresses.peek()[0];
                int room = (int)progresses.poll()[1];

                availables.add(room);
            }

            int room = availables.poll();
            long delayed = Math.max(time - meeting[0], 0);
            long startTime = meeting[0] + delayed;
            long endTime = meeting[1] + delayed;

            progresses.add(new long[] {endTime, room});

            counter[room]++;

            // System.out.println("room:%d, start:%d, end:%d, time:%d".formatted(room, startTime, endTime, time));
        }

        int max = 0;
        int ans = 0;

        System.out.println(Arrays.toString(counter));

        for (int i = 0; i < n; i++) {
            if (max < counter[i]) {
                max = counter[i];
                ans = i;
            }
        }

        return ans;
    }
}