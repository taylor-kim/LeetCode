class Solution {
    public int minimizeSum(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;

        if (n <= 3) return 0;

        Arrays.sort(nums);

        int sides = nums[n - 2] - nums[1];
        int left = nums[n - 1] - nums[2];
        int right = nums[n - 3] - nums[0];

        return Math.min(sides, Math.min(left, right));
    }
}