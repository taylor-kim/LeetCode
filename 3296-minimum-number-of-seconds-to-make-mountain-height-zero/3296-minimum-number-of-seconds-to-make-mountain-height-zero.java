class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        return mySol(mountainHeight, workerTimes);
    }

    public long mySol(int height, int[] times) {
        // t * 1 + t * 2 + t * 3... t * n => t * (1 + 2 + 3 + ... + n) => t * (n * (1 + n) / 2) <= ans

        // ans / t * 2 == n + n^2 == some. => n * (1 + n) == some

        // t * (h * (1 + h) / 2) == secs
        // (h * (1 + h)) <= ans / t * 2

        // 1 * (2 * (1 + 2) / 2) == 10
        // (h * (1 + h)) <= 10 / 1 * 2 <= 20

        long lo = 0;
        long hi = Long.MAX_VALUE;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;

            // System.out.println("lo:%d, hi:%d, mid:%d".formatted(lo, hi, mid));

            if (isPossible(times, height, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }

            // System.out.println("\n");
        }

        return lo;
    }

    private boolean isPossible(int[] times, int height, long seconds) {
        for (int time : times) {
            int reduced = getReducedAmount(time, seconds);

            // System.out.println("time:%d, reduced:%d, height:%d".formatted(time, reduced, height));

            height -= reduced;

            if (height <= 0) return true;
        }

        return false;
    }

    private int getReducedAmount(int time, long seconds) {
        int lo = 1;
        // int hi = Integer.MAX_VALUE;
        int hi = (int)1e5 + 1;

        // time * (h * (1 + h) / 2)
        // (n * (1 + n)) <= ans / t * 2

        // long target = (seconds * 2) / time;
        long target = (seconds * 2);

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            // long x = mid * (1l + mid);
            long x = mid * (1l + mid) * time;

            if (x <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }
}