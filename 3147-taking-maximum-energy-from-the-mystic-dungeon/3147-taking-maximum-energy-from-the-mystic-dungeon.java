class Solution {
    public int maximumEnergy(int[] energy, int k) {
        return mySol4_try_one_pass(energy, k);
    }

    public int mySol4_try_one_pass(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n + k];

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            dp[i + k] = Math.max(energy[i], energy[i] + dp[i]);

            if (i + k >= n) {
                ans = Math.max(ans, dp[i + k]);
            }
        }

        return ans;
    }

    public int official_reverse_traversal(int[] energy, int k) {
        int n = energy.length;
        int ans = Integer.MIN_VALUE;

        for (int i = n - 1; i + k >= n; i--) {
            int sum = 0;
            for (int j = i; j >= 0; j -= k) {
                sum += energy[j];
                ans = Math.max(ans, sum);
            }
        }

        return ans;
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