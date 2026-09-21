class Solution {
    public long[] resultArray(int[] nums, int k) {
        return mySol2_with_hint_and_gemini(nums, k);
    }

    public long[] mySol2_with_hint_and_gemini(int[] nums, int k) {
        int n = nums.length;
        long[] dp = new long[k];
        long[] ans = new long[k];

        for (int i = 0; i < n; i++) {
            long[] nextDp = new long[k];
            for (int x = 0; x < k; x++) {
                int index = (int)(1l * x * nums[i] % k);
                nextDp[index] += dp[x];
                ans[index] += dp[x];
            }
            nextDp[nums[i] % k]++;
            ans[nums[i] % k]++;
            dp = nextDp;
        }

        return ans;
    }

    public long[] mySol_hold(int[] nums, int k) {
        int n = nums.length;
        long[] pMul = new long[n + 1];
        pMul[0] = 1;

        for (int i = 0; i < n; i++) {
            pMul[i + 1] = pMul[i] * nums[i];
        }

        int[] ans = new int[k];

        for (int x = 0; x < k; x++) {
            long mul = 1;
            for (int left = 0, right = 0; right < n; right++) {
                mul *= nums[right];

                // if ()
            }
        }

        return null;
    }
}
