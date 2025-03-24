class Solution {
    public int countDays(int days, int[][] meetings) {
        return mySol(days, meetings);
    }

    public int mySol(int days, int[][] meetings) {
        int[] dp = new int[days + 2];

        for (int[] meeting : meetings) {
            dp[meeting[0]] += 1;
            dp[meeting[1] + 1] -= 1;
        }

        // System.out.println(Arrays.toString(dp));

        for (int i = 0; i < days; i++) {
            dp[i + 1] += dp[i];
        }

        // System.out.println(Arrays.toString(dp));

        int ans = 0;

        for (int i = 1; i <= days; i++) {
            if (dp[i] == 0) ans++;
        }

        return ans;
    }
}