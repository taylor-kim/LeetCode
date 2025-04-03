class Solution {
    public long maximumTripletValue(int[] nums) {
        return try_prefix_suffix_sum(nums);
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