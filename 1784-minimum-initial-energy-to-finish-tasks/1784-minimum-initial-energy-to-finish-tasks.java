class Solution {
    public int minimumEffort(int[][] tasks) {
        return mySol(tasks);
    }

    public int mySol(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> {
            // return a[0] != b[0] ? b[0] - a[0] : b[1] - a[1];
            return (b[1] - b[0]) - (a[1] - a[0]);
        });

        long sumOfRequired = 0;

        for (int[] task : tasks) {
            sumOfRequired += task[1];
        }

        long lo = 0;
        long hi = sumOfRequired;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;

            if (isPossible(tasks, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return (int)lo;
    }

    private boolean isPossible(int[][] tasks, long totalEnergy) {
        for (int[] task : tasks) {
            if (totalEnergy < task[1]) return false;

            totalEnergy -= task[0];

            if (totalEnergy < 0) return false;
        }

        return true;
    }
}