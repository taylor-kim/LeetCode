class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        return mySol(nums1, nums2);
    }

    public int[] mySol(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] ans = new int[n];

        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }

        for (int i = 0; i < n; i++) {
            int j = map.get(nums1[i]);

            ans[i] = -1;

            for (int k = j + 1; k < nums2.length; k++) {
                if (nums2[j] < nums2[k]) {
                    ans[i] = nums2[k];
                    break;
                }
            }
        }

        return ans;
    }
}