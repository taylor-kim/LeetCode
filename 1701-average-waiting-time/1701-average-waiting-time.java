class Solution {
    public double averageWaitingTime(int[][] customers) {
        return official(customers);
    }

    public double official(int[][] customers) {
        long totalWait = 0;
        int nextIdleTime = 0;

        for (int[] cus : customers) {
            nextIdleTime = Math.max(nextIdleTime, cus[0]) + cus[1];
            totalWait += nextIdleTime - cus[0];
        }

        return (double) totalWait / customers.length;
    }

    public double mySol_second(int[][] customers) {
        int time = 0;
        double waitingTime = 0.0;

        for (int[] cus : customers) {
            time = Math.max(time, cus[0]);
            int wait = time - cus[0];
            waitingTime += wait + cus[1];
            time += cus[1];
        }

        return waitingTime / customers.length;
    }

    public double mySol(int[][] customers) {
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
        }

        return waitingTime / customers.length;
    }
}