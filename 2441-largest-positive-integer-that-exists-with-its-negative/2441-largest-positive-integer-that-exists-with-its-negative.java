class Solution {
    public int findMaxK(int[] nums) {
        return mySol_set(nums);
    }

    public int mySol_set(int[] nums) {
        Set<Integer> set = new HashSet();

        int ans = -1;

        for (int num : nums) {
            if (set.contains(-num)) {
                ans = Math.max(ans, Math.abs(num));
            }
            set.add(num);
        }

        return ans;
    }
}