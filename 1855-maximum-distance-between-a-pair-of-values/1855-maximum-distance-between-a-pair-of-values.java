class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        return mySol(nums1, nums2);
    }

    public int mySol2(int[] nums1, int[] nums2) {
        int ans = 0;

        int start = 0;
        int end = 0;

        while (start < nums1.length && end < nums2.length) {
            if (nums1[start] <= nums2[end]) {
                ans = Math.max(ans, end++ - start);
            } else {
                if (++start > end) {
                    end = start;
                }
            }
        }

        return ans;
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