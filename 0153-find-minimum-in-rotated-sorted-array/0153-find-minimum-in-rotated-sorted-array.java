class Solution {
    public int findMin(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;

        // 5,6,7,1,2,3,4

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[lo] < nums[mid] && nums[mid] < nums[hi]) {
                hi = mid - 1;
            } else {
                if (nums[mid] > nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
        }

        return nums[lo];
    }
}