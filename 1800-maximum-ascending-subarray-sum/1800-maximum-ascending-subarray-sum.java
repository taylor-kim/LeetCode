class Solution {
    public int maxAscendingSum(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }

            ans = Math.max(ans, sum);
        }

        return ans;
    }
}