class Solution {
    public double averageWaitingTime(int[][] customers) {
        return mySol(customers);
    }

    public double mySol(int[][] customers) {
        // Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
        //     return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        // });

        Queue<int[]> pq = new LinkedList();

        for (int[] cus : customers) {
            pq.add(cus);
        }

        int time = 0;
        double waitingTime = 0.0;

        while (!pq.isEmpty()) {
            int[] cus = pq.poll();

            time = Math.max(time, cus[0]);
            int wait = Math.max(time - cus[0], 0);
            waitingTime += wait + cus[1];
            time += cus[1];

            // System.out.println(String.format("time:%d, wait:%d, totalWaitng:%f", time, wait + cus[1], waitingTime));
        }

        return waitingTime / customers.length;
    }
}