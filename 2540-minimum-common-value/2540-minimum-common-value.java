class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        return mySol(nums1, nums2);
    }

    public int mySol(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                return nums1[i];
            }
        }

        return -1;
    }
}