class Solution {
    public int maxValue(int[][] events, int k) {
        return mySol_after_editorial_title(events, k);
    }

    public int mySol_after_editorial_title(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> {
            return a[0] - b[0];
        });

        return topdown2(events, 0, k, new Integer[events.length][k + 1]);
    }

    public int topdown2(int[][] events, int index, int k, Integer[][] memo) {
        if (index >= events.length || k == 0) return 0;

        if (memo[index][k] != null) {
            return memo[index][k];
        }

        int exclude = topdown2(events, index + 1, k, memo);

        int next = leftmost(events, events[index][1] + 1);

        int include = events[index][2] + topdown2(events, next, k - 1, memo);

        return memo[index][k] = Math.max(include, exclude);
    }

    public int mySol_tle_mle(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> {
            return a[0] - b[0];
        });

        int max = 0;

        for (int[] event : events) {
            max = Math.max(max, event[1]);
        }
        
        int n = events.length;

        return topdown_dp(events, 0, 0, k, new Integer[n][max + 1][n]);
    }

    public int topdown_dp(int[][] events, int index, int prevEnd, int k, Integer[][][] memo) {
        if (index >= events.length || k == 0) return 0;

        if (memo[index][prevEnd][k] != null) {
            return memo[index][prevEnd][k];
        }

        int ans = topdown_dp(events, index + 1, prevEnd, k, memo);

        // int index = leftmost(events, index, day);

        int include = Integer.MIN_VALUE;

        if (prevEnd < events[index][0]) {
            ans = Math.max(ans, events[index][2] + topdown_dp(events, index + 1, events[index][1], k - 1, memo));
        }

        return memo[index][prevEnd][k] = ans;
    }

    private int leftmost(int[][] events, int target) {
        int lo = 0;
        int hi = events.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (target <= events[mid][0]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}