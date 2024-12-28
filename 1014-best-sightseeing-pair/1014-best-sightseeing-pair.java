class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        return after_editorial(values);
    }

    public int after_editorial(int[] values) {
        int n = values.length;
        int[] maxSoFar = new int[n];
        maxSoFar[0] = values[0];

        for (int i = 1; i < n; i++) {
            maxSoFar[i] = Math.max(maxSoFar[i - 1], values[i] + i);
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            int score = maxSoFar[i - 1] + values[i] - i;

            ans = Math.max(ans, score);
        }

        return ans;
    }

    public int mySol2_bf(int[] values) {
        int[][] sorted = new int[values.length][2];

        for (int i = 0; i < values.length; i++) {
            sorted[i][0] = values[i];
            sorted[i][1] = i;
        }

        Arrays.sort(sorted, (a, b) -> {
            return a[0] - b[0];
        });

        int n = sorted.length;

        return topdown(sorted, 0, n - 1, new Integer[n][n]);
    }

    private int topdown(int[][] sorted, int left, int right, Integer[][] memo) {
        if (left >= right) return Integer.MIN_VALUE;

        if (memo[left][right] != null) return memo[left][right];

        int ans = getScore(sorted, left, right);

        ans = Math.max(ans, topdown(sorted, left + 1, right, memo));
        ans = Math.max(ans, topdown(sorted, left, right - 1, memo));

        return memo[left][right] = ans;
    }

    public int mySol_fail(int[] values) {
        // index : 1,3,2,4,0
        // value : 1,2,5,6,8
        //

        // 1, 8 => 1 + 8 + 0 - 1 = 8
        // 2, 8 => 2 + 8 + 0 - 3 = 7
            // 1, 6 => 1 + 6 + 1 - 4 = 4
        // 5 + 8 + 0 - 2 = 11
            // 2 + 6 + 3 - 4 = 7
        // 5 + 6 + 2 - 4 = 9
        // 6 + 8 + 0 - 4 = 10

        int[][] sorted = new int[values.length][2];

        for (int i = 0; i < values.length; i++) {
            sorted[i][0] = values[i];
            sorted[i][1] = i;
        }

        Arrays.sort(sorted, (a, b) -> {
            return a[0] - b[0];
        });

        int ans = Integer.MIN_VALUE;

        int left = 0;
        int right = sorted.length - 1;

        while (left < right) {
            int score = getScore(sorted, left, right);
            ans = Math.max(ans, score);

            if (left + 1 == right) break;

            if (getScore(sorted, left + 1, right) < getScore(sorted, left, right - 1)) {
                right--;
            } else {
                left++;
            }
        }

        return ans;
    }

    private int getScore(int[][] arr, int left, int right) {
        return arr[left][0] + arr[right][0] - Math.abs(arr[left][1] - arr[right][1]);
    }
}