class Solution {
    public int countPartitions(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int left = 0;
        int right = 0;

        for (int num : nums) right += num;

        int ans = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            left += nums[i];
            right -= nums[i];

            if ((right - left) % 2 == 0) ans++;
        }

        return ans;
    }
}