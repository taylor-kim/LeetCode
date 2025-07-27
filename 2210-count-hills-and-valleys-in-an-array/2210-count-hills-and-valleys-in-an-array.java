class Solution {
    public int countHillValley(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = 0;

        int prev = 0;
        int current = 0;

        for (int i = 1; i < nums.length; i++) {
            current = nums[i] - nums[i - 1];

            if (current == 0) continue;

            if (current * prev < 0) ans++;

            prev = current;
        }

        return ans;
    }
}