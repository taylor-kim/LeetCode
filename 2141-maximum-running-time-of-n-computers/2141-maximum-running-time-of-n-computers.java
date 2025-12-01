class Solution {
    public long maxRunTime(int n, int[] batteries) {
        return mySol2(n, batteries);
    }

    public long mySol2(int n, int[] batteries) {
        if (n > batteries.length) return 0;

        Arrays.sort(batteries);

        long[] dp = new long[n];

        int offset = batteries.length - n;

        for (int i = 0; i < n; i++) {
            dp[i] = (long)batteries[i + offset];
        }

        long remains = 0;

        for (int i = 0; i < offset; i++) {
            remains += batteries[i];
        }

        long ans = dp[0];

        // System.out.println(Arrays.toString(dp));

        for (int i = 1; i < dp.length; i++) {
            long target = dp[i];
            long delta = dp[i] - dp[i - 1];

            // System.out.println(String.format("target:%d", target));

            int count = i;
            long sumOfDelta = delta * count;

            while (delta > 0 && remains < delta * count) {
                delta--;
            }

            if (delta > 0) {
                for (int j = 0; j < i; j++) {
                    // System.out.print(String.format("%d, ", dp[j]));

                    long diff = target - dp[j];
                    dp[j] = target;
                }

                remains -= delta * count;
                // System.out.println(String.format("\nremains:%d\n", remains));

                if (remains >= 0) {
                    ans += delta;
                } else {
                    break;
                }
            }
        }

        // System.out.println(String.format("ans:%d, remains:%d, n:%d", ans, remains, n));

        if (remains > 0 && remains >= n) {
            ans += remains / n;
        }
        
        return ans;
    }

    public long mySol(int n, int[] batteries) {
        if (n > batteries.length) return 0;

        Arrays.sort(batteries);

        long[] dp = new long[n];

        int offset = batteries.length - n;

        Map<Long, Integer> counter = new LinkedHashMap();
        Map<Long, Integer> indices = new LinkedHashMap();

        for (int i = 0; i < n; i++) {
            dp[i] = (long)batteries[i + offset];

            counter.put(dp[i], counter.getOrDefault(dp[i], 0) + 1);
            indices.put(dp[i], i);
        }

        System.out.println(Arrays.toString(batteries));
        System.out.println(Arrays.toString(dp));
        System.out.println(counter);

        long ans = dp[0];

        long remains = 0;

        for (int i = 0; i < offset; i++) {
            remains += batteries[i];
        }

        int i = 0;

        while (i < dp.length) {
            int count = counter.get(dp[i]);
            int next = indices.get(dp[i]) + 1;

            // System.out.println(String.format("count:%d, i:%d, next:%d, remains:%d", count, i, next, remains));

            if (next < dp.length) {
                long diff = dp[next] - dp[i];

                while (diff > 0 && remains < diff * count) {
                    diff--;
                }

                if (diff > 0) {
                    remains -= diff * count;
                    ans += diff;

                    counter.put(dp[next], counter.getOrDefault(dp[next], 0) + count);

                    System.out.println(String.format("i:%d, remains1:%d, ans:%d, diff:%d, count:%d", i, remains, ans, diff, count));
                }
            } else {
                long diff = remains / count;
                ans += diff;
                remains -= diff * count;

                System.out.println(String.format("i:%d, remains2:%d, ans:%d, diff:%d, count:%d", i, remains, ans, diff, count));
            }

            System.out.println(counter);

            i = next;
        }



        // for (int i = 0; i < offset; i++) {
        //     int spare = batteries[i];

        //     if (spare == 0) continue;

        //     int j = 1;

        //     while (j < dp.length && dp[j - 1] == dp[j]) j++;

        //     if (j >= dp.length) {
        //         // if (spare)

        //         System.out.println(String.format("spare:%d, j:%d", spare, j));

        //     } else {
        //         long diff = dp[j] - dp[j - 1];
        //         long fill = Math.min(diff, spare);

        //         dp[j - 1] += fill;

        //         batteries[i] -= fill;
        //     }
        // }

        return ans;
    }
}