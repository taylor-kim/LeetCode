class Solution {
    public long repairCars(int[] ranks, int cars) {
        return mySol(ranks, cars);
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