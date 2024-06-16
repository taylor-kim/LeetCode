class Solution {
    public int minimumAddedCoins(int[] coins, int target) {
        return others(coins, target);
    }

    public int others(int[] coins, int target) {
        Arrays.sort(coins);

        int x = 0;
        int ans = 0;
        int index = 0;

        while (x < target) {
            if (index < coins.length && coins[index] <= x + 1) {
                x += coins[index++];
            } else {
                x += x + 1;
                ans++;
            }
        }

        return ans;
    }

    public int others_dp_TLE(int[] coins, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int i = 0; i < coins.length; i++) {
            for (int t = target; t >= coins[i]; t--) {
                dp[t] = dp[t] || dp[t - coins[i]];
            }
        }

        int ans = 0;

        for (int newCoin = 1; newCoin <= target; newCoin++) {
            if (!dp[newCoin]) {
                ans++;

                for (int t = target; t >= newCoin; t--) {
                    dp[t] = dp[t] || dp[t - newCoin];
                }
            }
        }

        return ans;
    }

    public int mySol_TLE(int[] coins, int target) {
        List<Integer> list = new ArrayList();
        for (int coin : coins) {
            list.add(coin);
        }

        Collections.sort(list);

        int ans = 0;

        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < i; j++) {
                if (!makeTarget(list, 0, i)) {
                    int index = Collections.binarySearch(list, i);
                    if (index < 0) index = -(index + 1);
                    list.add(index, i);
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean makeTarget(List<Integer> list, int index, int target) {
        if (target == 0) return true;
        if (index >= list.size() || list.get(index) > target) return false;

        return makeTarget(list, index + 1, target - list.get(index))
        || makeTarget(list, index + 1, target);
    }
}