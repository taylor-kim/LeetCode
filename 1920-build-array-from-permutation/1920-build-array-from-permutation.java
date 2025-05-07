class Solution {
    public int[] buildArray(int[] nums) {
        return try_inplace(nums);
    }

    public int[] try_inplace(int[] nums) {
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            int newIndex = nums[i] % 1000;
            int newValue = (nums[newIndex] % 1000) * 1000 + nums[i];

            nums[i] = newValue;

            // System.out.println(String.format("newIndex:%d, newValue:%d", newIndex, newValue));
        }

        for (int i = 0; i < n; i++) {
            nums[i] /= 1000;
        }

        return nums;
    }

    public int[] mySol(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = nums[nums[i]];
        }

        return ans;
    }
}