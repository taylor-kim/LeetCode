class Solution {
    public boolean isTrionic(int[] nums) {
        return mySol(nums);
    }

    public boolean mySol(int[] nums) {
        int n = nums.length;
        boolean inc = true;
        List<Boolean> list = new ArrayList();

        if (nums[0] >= nums[1]) return false;

        list.add(inc);

        for (int i = 1; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) return false;

            if (nums[i] < nums[i + 1] && !inc) {
                inc = !inc;
                list.add(inc);
            } else if (nums[i] > nums[i + 1] && inc) {
                inc = !inc;
                list.add(inc);
            }
        }

        return list.size() == 3 && list.get(0) && !list.get(1) && list.get(2);
    }
}