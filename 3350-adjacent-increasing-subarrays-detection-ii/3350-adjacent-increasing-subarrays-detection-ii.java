class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        return mySol(nums);
    }

    public int mySol(List<Integer> nums) {
        int ans = 0;
        int count = 1;
        int prevCount = 0;

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) < nums.get(i)) {
                count++;
            } else {
                prevCount = count;
                count = 1;
            }

            ans = Math.max(ans, Math.min(prevCount, count));
            ans = Math.max(ans, count / 2);
        }

        return ans;
    }
}