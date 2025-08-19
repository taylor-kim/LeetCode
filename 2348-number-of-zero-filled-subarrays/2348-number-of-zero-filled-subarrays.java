class Solution {
    public long zeroFilledSubarray(int[] nums) {
        return mySol(nums);
    }

    public long mySol(int[] nums) {
        long ans = 0;
        int left = 0;
        long sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (left <= right && sum > 0) {
                sum -= nums[left++];
            }

            ans += right - left + 1;
        }

        return ans;
    }
}