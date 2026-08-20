class Solution {
    public int[] resultArray(int[] nums) {
        return mySol(nums);
    }

    public int[] mySol(int[] nums) {
        int n = nums.length;
        List<Integer> list1 = new ArrayList();
        List<Integer> list2 = new ArrayList();

        list1.add(nums[0]);
        list2.add(nums[1]);

        int last1 = list1.get(0);
        int last2 = list2.get(0);

        for (int i = 2; i < n; i++) {
            if (last1 > last2) {
                last1 = nums[i];
                list1.add(last1);
            } else {
                last2 = nums[i];
                list2.add(last2);
            }
        }

        int[] ans = new int[n];
        int index = 0;

        for (int num : list1) {
            ans[index++] = num;
        }

        for (int num : list2) {
            ans[index++] = num;
        }

        return ans;
    }
}