class Solution {
    public int largestPerimeter(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        int ans = 0;

        for (int i = 0; i < n - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < n - 1; j++) {
                while (k < n && nums[i] + nums[j] > nums[k]) {
                    k++;
                }

                if (j < k - 1) {
                    ans = Math.max(ans, nums[i] + nums[j] + nums[k - 1]);
                }
            }
        }

        return ans;
    }
}