class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        for (int num : nums1) {
            int idx = lowerBound(nums2, num);
            if (idx < nums2.length && nums2[idx] == num) {
                return num;
            }
        }
        return -1;
    }

    private int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
