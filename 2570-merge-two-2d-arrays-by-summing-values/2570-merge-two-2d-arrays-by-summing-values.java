class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        return mySol(nums1, nums2);
    }

    public int[][] mySol(int[][] nums1, int[][] nums2) {
        int i1 = 0;
        int i2 = 0;

        List<int[]> list = new ArrayList();

        while (i1 < nums1.length && i2 < nums2.length) {
            int id1 = nums1[i1][0];
            int id2 = nums2[i2][0];

            int val1 = nums1[i1][1];
            int val2 = nums2[i2][1];

            if (id1 == id2) {
                list.add(new int[] {id1, val1 + val2});
                i1++;
                i2++;
            } else if (id1 < id2) {
                list.add(new int[] {id1, val1});
                i1++;
            } else {
                list.add(new int[] {id2, val2});
                i2++;
            }
        }

        while (i1 < nums1.length) {
            list.add(nums1[i1++]);
        }

        while (i2 < nums2.length) {
            list.add(nums2[i2++]);
        }

        int[][] ans = new int[list.size()][2];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}