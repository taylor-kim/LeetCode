class Solution {
    public int subsequencePairCount(int[] nums) {
        return after_editorial(nums);
    }

    public int after_editorial(int[] nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);

        int[][] dp = new int[max + 1][max + 1];
        dp[0][0] = 1;

        int mod = (int)1e9 + 7;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            int[][] dp2 = new int[max + 1][max + 1];
            
            for (int j = 0; j <= max; j++) {
                for (int k = 0; k <= max; k++) {
                    if (dp[j][k] == 0) continue;

                    int jj = gcd(j, num);
                    int kk = gcd(k, num);

                    dp2[j][k] = (dp2[j][k] + dp[j][k]) % mod;
                    dp2[jj][k] = (dp2[jj][k] + dp[j][k]) % mod;
                    dp2[j][kk] = (dp2[j][kk] + dp[j][k]) % mod;
                }
            }

            dp = dp2;
        }

        int ans = 0;

        for (int i = 1; i <= max; i++) {
            ans = (ans + dp[i][i]) % mod;
        }

        return ans;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;

        return gcd(b, a % b);
    }
}