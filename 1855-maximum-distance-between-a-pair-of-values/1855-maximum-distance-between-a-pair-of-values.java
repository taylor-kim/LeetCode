class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        return mySol(nums1, nums2);
    }

    public int mySol(int[] nums1, int[] nums2) {
        int ans = 0;

        for (int i = 0; i < nums1.length; i++) {
            ans = Math.max(ans, rightmost(nums2, nums1[i], i) - i);
        }

        return ans;
    }

    private int rightmost(int[] nums, int target, int lo) {
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] >= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }
}