class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        return mySol_without_extspace(nums, k);
    }

    public int try_binarysearch(int[] nums, int k) {
        return -1;
    }

    public int mySol_with_prefixprod_fail(int[] nums, int k) {
        int n = nums.length;

        int[] pMul = new int[n + 1];
        pMul[0] = 1;

        for (int i = 0; i < n; i++) {
            pMul[i + 1] = pMul[i] * nums[i];
        }

        int left = 0;

        int ans = 0;

        for (int right = 0; right < n; right++) {
            while (left <= right && pMul[right + 1] / pMul[left] >= k) {
                left++;
            }

            ans += right - left + 1;
        }

        return ans;
    }

    public int mySol_without_extspace(int[] nums, int k) {
        if (k <= 1) return 0;

        int n = nums.length;

        int ans = 0;

        int left = 0;

        int product = 1;
        
        for (int right = 0; right < n; right++) {
            product *= nums[right];

            while (product >= k) {
                product /= nums[left++];
            }

            ans += right - left + 1;
        }

        return ans;
    }

    public int mySol(int[] nums, int k) {
        int n = nums.length;

        int[] dp = new int[n + 1];

        int left = 0;

        int product = 1;
        
        for (int right = 0; right < n; right++) {
            product *= nums[right];

            while (left <= right && product >= k) {
                product /= nums[left++];
            }

            dp[right + 1] += dp[right] + right - left + 1;
        }

        return dp[n];
    }
}