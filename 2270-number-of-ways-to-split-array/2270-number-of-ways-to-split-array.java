class Solution {
    public int waysToSplitArray(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int ans = 0;

        long rightSum = 0;
        long leftSum = 0;

        for (int num : nums) rightSum += num;

        for (int i = 0; i < n - 1; i++) {
            leftSum += nums[i];
            rightSum -= nums[i];

            // System.out.println(String.format("leftSum:%d, rightSum:%d", leftSum, rightSum));

            if (leftSum >= rightSum) ans++;
        }

        return ans;
    }
}