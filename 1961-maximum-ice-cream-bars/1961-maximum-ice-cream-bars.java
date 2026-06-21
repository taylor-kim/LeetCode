class Solution {
    public int maxIceCream(int[] costs, int coins) {
        return mySol(costs, coins);
    }

    public int mySol(int[] costs, int coins) {
        int max = 0;
        for (int cost : costs) {
            max = Math.max(max, cost);
        }
        int[] bucket = new int[max + 1];

        for (int cost : costs) {
            bucket[cost]++;
        }

        int ans = 0;
        int cost = 1;

        while (cost <= max && coins > 0) {
            if (bucket[cost] == 0) {
                cost++;
            } else {
                coins -= cost;

                if (coins >= 0) ans++;

                bucket[cost]--;
            }
        }

        return ans;
    }
}