class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        return mySol(nums1, nums2);
    }

    public int[] mySol(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList();
        Set<Integer> set = new HashSet();

        for (int num : nums1) {
            set.add(num);
        }

        for (int num : nums2) {
            if (set.remove(num)) {
                list.add(num);
            }
        }

        int[] ans = new int[list.size()];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}