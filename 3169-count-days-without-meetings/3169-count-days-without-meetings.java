class Solution {
    public int countDays(int days, int[][] meetings) {
        return try_lineSweep_with_treemap(days, meetings);
    }

    public int try_lineSweep_with_treemap(int days, int[][] meetings) {
        TreeMap<Integer, Integer> treeMap = new TreeMap();
        int prevDay = days;

        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];

            treeMap.put(start, treeMap.getOrDefault(start, 0) + 1);
            treeMap.put(end + 1, treeMap.getOrDefault(end + 1, 0) - 1);

            prevDay = Math.min(prevDay, start);
        }

        int ans = prevDay - 1;
        int pSum = 0;

        for (int day : treeMap.keySet()) {
            int count = treeMap.get(day);

            if (pSum == 0) {
                ans += day - prevDay;
            }

            pSum += count;
            prevDay = day;
        }

        ans += days - prevDay + 1;

        return ans;
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