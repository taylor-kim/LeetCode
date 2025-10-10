class Solution {
    public int maximumEnergy(int[] energy, int k) {
        return mySol3(energy, k);
    }

    public int mySol3(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n + k];

        for (int i = 0; i < n; i++) {
            dp[i + k] = Math.max(energy[i], energy[i] + dp[i]);
        }

        int ans = Integer.MIN_VALUE;

        for (int i = n; i < n + k; i++) {
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public int mySol2(int[] energy, int k) {
        int ans = Integer.MIN_VALUE;

        Integer[] memo = new Integer[energy.length];

        for (int i = 0; i < energy.length; i++) {
            ans = Math.max(ans, topdown(i, energy, k, memo));
        }

        return ans;
    }

    public int topdown(int index, int[] energy, int k, Integer[] memo) {
        if (index >= energy.length) return 0;

        if (memo[index] != null) return memo[index];

        return memo[index] = energy[index] + topdown(index + k, energy, k, memo);
    }

    public int mySol_fail(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n + k];

        for (int i = 0; i < n; i++) {
            dp[i + k] = energy[i] + dp[i];
        }

        return dp[n + k - 1];
    }
}