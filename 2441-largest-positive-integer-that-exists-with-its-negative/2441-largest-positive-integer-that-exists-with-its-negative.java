class Solution {
    public int findMaxK(int[] nums) {
        return mySol_set(nums);
    }

    public int mySol_set(int[] nums) {
        Set<Integer> set = new HashSet();

        int ans = -1;

        for (int num : nums) {
            int abs = Math.abs(num);

            if (abs > ans && set.contains(-num)) {
                ans = Math.max(ans, abs);
            }
            set.add(num);
        }

        return ans;
    }
}