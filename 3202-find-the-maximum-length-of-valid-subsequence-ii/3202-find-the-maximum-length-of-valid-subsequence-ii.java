class Solution {
    public int maximumLength(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int official_dp(int[] nums, int k) {
        int[][] dp = new int[k][k];
        int ans = 0;

        for (int num : nums) {
            num %= k;
            for (int prev = 0; prev < k; prev++) {
                dp[prev][num] = dp[num][prev] + 1;
                ans = Math.max(ans, dp[prev][num]);
            }
        }

        return ans;
    }

    public int mySol(int[] nums, int k) {
        // k == 3
        // 0,1,2,0,1,2
        // 3,4,5,3,4,5

        // k == 2
        // 1,2,3,4,5
        // 1,0,1,0,1
        //
        int[][] dp = new int[k][k];

        int ans = 0;

        for (int num : nums) {
            int b = num % k;
            
            for (int mod = 0; mod < k; mod++) {
                int a = (mod - b + k) % k;

                // if (dp[mod][a] > 0) {
                //     dp[mod][b] = Math.max(dp[mod][b], dp[mod][a] + 1);
                //     ans = Math.max(ans, dp[mod][b]);
                // }

                dp[mod][b] = Math.max(dp[mod][b], dp[mod][a] + 1);
                ans = Math.max(ans, dp[mod][b]);
            }

            // for (int mod = 0; mod < k; mod++) {
            //     if (dp[mod][b] == 0) {
            //         dp[mod][b] = 1;
            //     }
            // }
        }

        return ans;
    }
}