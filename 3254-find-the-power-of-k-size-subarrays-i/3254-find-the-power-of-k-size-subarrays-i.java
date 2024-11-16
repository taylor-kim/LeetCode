class Solution {
    public int[] resultsArray(int[] nums, int k) {
        return mySol2(nums, k);
    }

    public int[] mySol2(int[] nums, int k) {
        if (k == 1) return nums;

        int left = 0;
        int right = 0;

        int[] ans = new int[nums.length - k + 1];
        
        while (left < ans.length && right < nums.length) {
            while (left < ans.length && left < right && right > 0 && nums[right - 1] + 1 != nums[right]) {
                ans[left++] = -1;
            }

            if (right - left + 1 == k) {
                ans[left++] = nums[right];
            }

            right++;
        }

        return ans;
    }

    public int[] mySol_fail(int[] nums, int k) {
        if (k == 1) return nums;

        int left = 0;
        int right = 0;

        int[] ans = new int[nums.length - k + 1];
        
        while (left < ans.length && right < nums.length) {
            if (left < right && nums[left] >= nums[right]) {
                ans[left++] = -1;
                continue;
            }

            if (right > 0 && nums[right - 1] + 1 < nums[right]) {
                ans[left++] = -1;
                right = left + 1;
                continue;
            }

            if (right - left + 1 == k) {
                // if (nums[right] - nums[left] + 1 == k) {
                //     ans[left++] = nums[right];
                // } else {
                //     ans[left++] = -1;
                // }
                ans[left++] = nums[right];
            }

            right++;
        }

        return ans;
    }
}