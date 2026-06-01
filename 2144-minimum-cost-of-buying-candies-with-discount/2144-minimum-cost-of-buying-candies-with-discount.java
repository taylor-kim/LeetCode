class Solution {
    public int minimumCost(int[] cost) {
        return mySol(cost);
    }

    public int mySol(int[] cost) {
        Arrays.sort(cost);

        int ans = 0;

        for (int i = cost.length - 1; i >= 0; i = i - 3) {
            ans += cost[i];

            if (i - 1 >= 0) {
                ans += cost[i - 1];
            }
        }

        return ans;
    }
}