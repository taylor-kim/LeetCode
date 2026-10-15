class Solution {
    public int sumCounts(List<Integer> nums) {
        return mySol(nums);
    }

    public int mySol(List<Integer> nums) {
        int n = nums.size();
        int ans = 0;
        Set<Integer> set = new HashSet();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                set.add(nums.get(j));
                ans += set.size() * set.size();
            }

            set.clear();
        }

        return ans;
    }
}