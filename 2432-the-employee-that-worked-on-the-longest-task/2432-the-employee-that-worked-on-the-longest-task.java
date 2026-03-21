class Solution {
    public int hardestWorker(int n, int[][] logs) {
        return mySol(n, logs);
    }

    public int mySol(int n, int[][] logs) {
        int ans = n;
        int longest = 0;
        int prevEnd = 0;

        for (int[] log : logs) {
            int duration = log[1] - prevEnd;

            if (longest < duration) {
                longest = duration;
                ans = log[0];
            } else if (longest == duration) {
                ans = Math.min(ans, log[0]);
            }

            prevEnd = log[1];
        }

        return ans;
    }
}