class Solution {
    public int[][] divideArray(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int[][] mySol(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        int[][] ans = new int[n / 3][3];

        for (int i = 0; i < n - 2; i+=3) {
            if (nums[i + 2] - nums[i] > k) return new int[][] {};

            ans[i / 3][0] = nums[i];
            ans[i / 3][1] = nums[i + 1];
            ans[i / 3][2] = nums[i + 2];
        }

        return ans;
    }
}