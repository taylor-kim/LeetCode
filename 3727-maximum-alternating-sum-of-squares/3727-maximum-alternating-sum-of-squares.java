class Solution {
    public long maxAlternatingSum(int[] nums) {
        return mySol(nums);
    }

    public long mySol(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) nums[i] = -nums[i];
        }

        Arrays.sort(nums);

        long ans = 0;

        for (int i = 0; i < n / 2; i++) {
            ans -= (long)nums[i] * nums[i];
        }

        for (int i = n / 2; i < n; i++) {
            ans += (long)nums[i] * nums[i];
        }

        return ans;
    }
}