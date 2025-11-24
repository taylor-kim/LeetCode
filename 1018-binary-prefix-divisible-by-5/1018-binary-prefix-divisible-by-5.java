class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        return mySol(nums);
    }

    public List<Boolean> mySol(int[] nums) {
        List<Boolean> ans = new ArrayList();

        int x = 0;

        for (int num : nums) {
            x = (x << 1) | num;

            ans.add(x % 5 == 0);

            x %= 5;
        }

        return ans;
    }
}