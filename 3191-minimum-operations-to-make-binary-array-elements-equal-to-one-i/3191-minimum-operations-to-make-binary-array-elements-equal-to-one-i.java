class Solution {
    public int minOperations(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        int ans = 0;

        while (left < right) {
            if (left + 2 < n && nums[left] == 0) {
                ans++;
                flip(nums, left);
                flip(nums, left + 1);
                flip(nums, left + 2);
            }

            while (left < right && nums[left] == 1) {
                left++;
            }

            if (right - 2 >= 0 && nums[right] == 0) {
                ans++;
                flip(nums, right);
                flip(nums, right - 1);
                flip(nums, right - 2);
            }

            while (left < right && nums[right] == 1) {
                right--;
            }
        }

        for (int num : nums) {
            if (num == 0) return -1;
        }

        return ans;
    }

    private void flip(int[] nums, int index) {
        nums[index] = 1 - nums[index];
    }
}