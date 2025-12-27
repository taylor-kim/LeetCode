class Solution {
    public int mostBooked(int n, int[][] meetings) {
        return mySol(n, meetings);
    }

    public int mySol(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> {
            return a[0] - b[0];
        });

        Queue<Integer> availables = new PriorityQueue();
        
        for (int i = 0; i < n; i++) {
            availables.add(i);
        }

        Queue<long[]> progresses = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]);
        });

        int[] counter = new int[n];

        for (int[] meeting : meetings) {
            while (!progresses.isEmpty() && progresses.peek()[0] <= meeting[0]) {
                int room = (int)progresses.poll()[1];

                availables.add(room);
            }

            long delayed = 0;

            if (availables.isEmpty()) {
                delayed = progresses.peek()[0] - meeting[0];
                int room = (int)progresses.poll()[1];

                availables.add(room);
            }

            int room = availables.poll();
            long endTime = meeting[1] + delayed;

            progresses.add(new long[] {endTime, room});

            counter[room]++;
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