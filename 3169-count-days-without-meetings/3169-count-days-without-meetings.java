class Solution {
    public int countDays(int days, int[][] meetings) {
        return mySol2(days, meetings);
    }

    public int mySol2(int days, int[][] meetings) {
        List<int[]> list = new ArrayList();

        for (int[] meeting : meetings) {
            list.add(new int[] {meeting[0], meeting[1]});
        }

        Collections.sort(list, (a, b) -> {
            return a[0] - b[0];
        });

        int ans = 0;
        int prevEnd = 0;

        for (int i = 0; i < list.size(); i++) {
            int[] current = list.get(i);

            // System.out.println(String.format("prevEnd:%d, current:%s", prevEnd, Arrays.toString(current)));

            if (prevEnd + 1 < current[0]) {
                ans += current[0] - (prevEnd + 1);
                prevEnd = current[1];
            } else {
                prevEnd = Math.max(prevEnd, current[1]);
            }
        }

        if (prevEnd < days) {
            ans += days - prevEnd;
        }

        return ans;
    }

    public int mySol_mle(int days, int[][] meetings) {
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