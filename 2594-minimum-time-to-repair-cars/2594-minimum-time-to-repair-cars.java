class Solution {
    public long repairCars(int[] ranks, int cars) {
        return official_minHeap(ranks, cars);
    }

    public long official_minHeap(int[] ranks, int cars) {
        Map<Integer, Integer> freq = new HashMap();

        for (int rank : ranks) {
            freq.put(rank, freq.getOrDefault(rank, 0) + 1);
        }

        Queue<long[]> pq = new PriorityQueue<>((a, b) -> {
            return (int)(a[0] - b[0]);
        });

        for (int rank : freq.keySet()) {
            int count = freq.get(rank);

            // time, rank, fixedCar, mechCount
            pq.add(new long[] {rank, rank, 1, count});
        }

        long time = 0;

        while (cars > 0) {
            long[] data = pq.poll();

            time = data[0];
            int rank = (int)data[1];
            long fixed = data[2];
            int count = (int)data[3];

            cars -= count;

            fixed++;

            pq.add(new long[] {rank * fixed * fixed, rank, fixed, count});
        }

        return time;
    }

    public long mySol(int[] ranks, int cars) {
        int n = ranks.length;

        long max = 0;

        for (int rank : ranks) max = Math.max(max, rank);

        long lo = 1;
        long hi = max * cars * cars;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;

            if (isPossible(ranks, cars, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean isPossible(int[] ranks, int cars, long minutes) {
        for (int i = 0; i < ranks.length && cars > 0; i++) {
            int rank = ranks[i];

            cars -= (int)Math.sqrt(minutes / rank);
        }

        return cars <= 0;
    }
}