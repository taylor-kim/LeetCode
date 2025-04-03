class Solution {
    public long maximumTripletValue(int[] nums) {
        return try_greedy_spaceopt(nums);
    }

    public long try_greedy_spaceopt(int[] nums) {
        int n = nums.length;
        long ans = 0, imax = Math.max(nums[0], nums[1]), dmax = Math.max(0, nums[0] - nums[1]);

        for (int k = 2; k < n; k++) {
            ans = Math.max(ans, dmax * nums[k]);
            dmax = Math.max(dmax, imax - nums[k]);
            imax = Math.max(imax, nums[k]);
        }

        return ans;
    }

    public long official_greedy_spaceopt(int[] nums) {
        int n = nums.length;
        long ans = 0, imax = 0, dmax = 0;

        for (int k = 0; k < n; k++) {
            ans = Math.max(ans, dmax * nums[k]);
            dmax = Math.max(dmax, imax - nums[k]);
            imax = Math.max(imax, nums[k]);
        }

        return ans;
    }

    public long try_prefix_suffix_sum(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i - 1]);

            rightMax[n - i - 1] = Math.max(rightMax[n - i], nums[n - i]);
        }

        long ans = 0;

        for (int j = 1; j < n - 1; j++) {
            ans = Math.max(1l * (leftMax[j] - nums[j]) * rightMax[j], ans);
        }

        return ans;
    }
}