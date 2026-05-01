class Solution {
    public int maxRotateFunction(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int total = 0;

        for (int num : nums) total += num;

        int sum = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            sum += i * nums[i];
        }

        ans = sum;

        for (int i = 0; i < n - 1; i++) {
            int deduct = total - nums[i];
            sum = sum - deduct + (nums[i] * (n - 1));

            ans = Math.max(ans, sum);
        }

        return ans;
    }
}