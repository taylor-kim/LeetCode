class Solution {
    public int[] productExceptSelf(int[] nums) {
        return others(nums);
    }

    public int[] others2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        Arrays.fill(ans, 1);

        int cur = 1;

        for (int i = 0; i < n; i++) {
            ans[i] *= cur;
            cur *= nums[i];
        }

        cur = 1;

        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= cur;
            cur *= nums[i];
        }

        return ans;
    }

    public int[] others(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        result[0] = 1;

        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int right = 1;

        for (int i = n - 2; i >= 0; i--) {
            right *= nums[i + 1];
            result[i] *= right;
        }

        return result;
    }
}