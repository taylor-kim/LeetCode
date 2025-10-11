class Solution {
    public long maximumTotalDamage(int[] power) {
        return official(power);
    }

    public long official(int[] power) {
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < power.length; i++) {
            map.put(power[i], map.getOrDefault(power[i], 0) + 1);
        }

        int[][] arr = new int[map.size()][2];
        int index = 0;

        for (int key : map.keySet()) {
            arr[index][0] = key;
            arr[index++][1] = map.get(key);
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        long[] dp = new long[arr.length];
        long ans = 0;
        long max = 0;
        int j = 0;

        for (int i = 0; i < arr.length; i++) {
            while (j < i && arr[j][0] + 2 < arr[i][0]) {
                // max = Math.max(max, dp[j++]);
                dp[j] = Math.max(dp[j], dp[Math.max(j - 1, 0)]);
                j++;
            }
            // dp[i] = max + ((long)arr[i][0] * arr[i][1]);
            dp[i] = (j - 1 >= 0 ? dp[j - 1] : 0) + ((long)arr[i][0] * arr[i][1]);

            ans = Math.max(ans, dp[i]);
        }

        // j = 0, i = 0, dp[0] = 1
        // j = 0, i = 1, dp[i] = 

        return ans;
    }

    public long mySol5(int[] power) {
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < power.length; i++) {
            map.put(power[i], map.getOrDefault(power[i], 0) + 1);
        }

        int[][] arr = new int[map.size()][2];
        int index = 0;

        for (int key : map.keySet()) {
            arr[index][0] = key;
            arr[index++][1] = map.get(key);
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        return topdown(arr, 0, new Long[arr.length], new HashSet());
    }

    public long topdown(int[][] arr, int index, Long[] memo, Set<Integer> impossible) {
        if (index >= arr.length) return 0;

        // if (memo[index] != null) return memo[index];

        int num = arr[index][0];

        if (impossible.contains(num)) {
            return topdown(arr, index + 1, memo, impossible);
        }

        for (int d = 1; d <= 2; d++) {
            impossible.add(num + d);
            impossible.add(num - d);
        }

        long include = (long)(arr[index][0] * arr[index][1]) + topdown(arr, index + 1, memo, impossible);

        for (int d = 1; d <= 2; d++) {
            impossible.remove(num + d);
            impossible.remove(num - d);
        }

        return memo[index] = Math.max(include, 0l);
    }

    public long mySol4_mle(int[] power) {
        Map<Integer, Integer> map = new HashMap();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < power.length; i++) {
            map.put(power[i], map.getOrDefault(power[i], 0) + 1);
            max = Math.max(power[i], max);
            min = Math.min(power[i], min);
        }

        int[][] arr = new int[map.size()][2];
        int index = 0;

        for (int key : map.keySet()) {
            arr[index][0] = key;
            arr[index++][1] = map.get(key);
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        return topdown(arr, -1, 0, new Long[arr.length + 1][arr.length + 1]);
    }

    public long topdown(int[][] arr, int prev, int index, Long[][] memo) {
        if (index >= arr.length) return 0;

        long ans = 0;

        if (memo[prev + 1][index + 1] != null) return memo[prev + 1][index + 1];

        if (prev < 0 || arr[prev][0] + 2 < arr[index][0]) {
            ans = arr[index][0] * arr[index][1] + topdown(arr, index, index + 1, memo);
        }

        // long exclude = topdown(arr, prev, index + 1, memo);
        long exclude = 0;

        return memo[prev + 1][index + 1] = Math.max(ans, exclude);
    }

    public long mySol3_holding(int[] power) {
        Map<Integer, Integer> map = new HashMap();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < power.length; i++) {
            map.put(power[i], map.getOrDefault(power[i], 0) + 1);
            max = Math.max(power[i], max);
            min = Math.min(power[i], min);
        }

        int[][] arr = new int[map.size()][2];

        int index = 0;

        for (int key : map.keySet()) {
            arr[index][0] = key;
            arr[index++][1] = map.get(key);
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        // dp[p] => max sum when choose power[i]
        // dp[p] = 

        long[] dp = new long[max - min + 7 + 3];

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i][0];
            int count = arr[i][1];

            dp[num + 3] += dp[num - 3];
        }

        return 0;
    }

    public long mySol2_fail(int[] power) {
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < power.length; i++) {
            map.put(power[i], map.getOrDefault(power[i], 0) + 1);
        }

        int[][] arr = new int[map.size()][2];

        int index = 0;

        for (int key : map.keySet()) {
            arr[index][0] = key;
            arr[index++][1] = map.get(key);
        }

        Arrays.sort(arr, (a, b) -> {
            return b[0] * b[1] - a[0] * a[1];
        });

        long ans = 0;

        for (int base = 0; base < 5; base++) {
            long sub = 0;
            Set<Integer> impossible = new HashSet();

            for (int i = base; i < arr.length; i++) {
                int value = arr[i][0];
                int count = arr[i][1];

                if (impossible.contains(value)) continue;

                System.out.println(String.format("v:%d, c:%d", value, count));

                sub += value * count;

                for (int d = 1; d <= 2; d++) {
                    impossible.add(value + d);
                    impossible.add(value - d);
                }
            }

            ans = Math.max(ans, sub);

            System.out.println(String.format("\n"));
        }

        return ans;
    }

    public long mySol_fail(int[] power) {
        Arrays.sort(power);
        return topdown(power, 0, new HashSet(), new HashSet());
    }

    public long topdown(int[] power, int index, Set<Integer> visit, Set<Integer> impossible) {
        if (index >= power.length) return 0;

        int num = power[index];

        long ans = 0;

        if (impossible.contains(num)) {
            ans = topdown(power, index + 1, visit, impossible);
        } else {
            if (visit.add(num)) {
                for (int i = 1; i <= 2; i++) {
                    impossible.add(num + i);
                    impossible.add(num - i);
                }
            }

            ans = (long)num + topdown(power, index + 1, visit, impossible);
        }

        ans = Math.max(ans, topdown(power, index + 1, visit, impossible));

        return ans;
    }
}