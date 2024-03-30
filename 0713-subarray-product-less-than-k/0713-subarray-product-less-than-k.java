class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        return official_binarysearch_with_log(nums, k);
    }

    public int official_binarysearch_with_log(int[] nums, int k) {
        if (k == 0) return 0;

        double logK = Math.log(k);
        int m = nums.length + 1;
        double[] pSum = new double[m];

        for (int i = 0; i < nums.length; i++) {
            pSum[i + 1] = pSum[i] + Math.log(nums[i]);
        }

        int ans = 0;

        for (int i = 0; i < m; i++) {
            int lo = i + 1;
            int hi = m;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (pSum[mid] < pSum[i] + logK - 1e-9) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            ans += lo - i - 1;
        }

        return ans;
    }

    public int try_with_log_no_extspace(int[] nums, int k) {
        int n = nums.length;

        int ans = 0;

        int left = 0;

        double sum = 0;
        double logK = Math.log(k);

        for (int right = 0; right < n; right++) {
            sum += Math.log(nums[right]);

            while (left <= right && sum >= logK) {
                sum -= Math.log(nums[left++]);
            }

            ans = right - left + 1;
        }

        return ans;
    }

    public int try_prefixsum_with_log(int[] nums, int k) {
        int n = nums.length;

        double[] pSum = new double[n + 1];
        double logK = Math.log(k);

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + Math.log(nums[i]);
        }

        int left = 0;

        int ans = 0;

        for (int right = 0; right < n; right++) {
            while (left <= right && pSum[right + 1] - pSum[left] >= logK) {
                left++;
            }

            ans += right - left + 1;
        }

        return ans;
    }

    public int mySol_with_prefixprod_dueto_overflow(int[] nums, int k) {
        int n = nums.length;

        long[] pMul = new long[n + 1];
        pMul[0] = 1;

        for (int i = 0; i < n; i++) {
            pMul[i + 1] = pMul[i] * nums[i];

            if (pMul[i + 1] < pMul[i]) throw new RuntimeException("overflow");
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