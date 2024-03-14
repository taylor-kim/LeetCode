class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return mySol(nums, goal);
    }

    public int mySol(int[] nums, int goal) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int base = 0;

            for (int j = i; j < nums.length; j++) {
                base += nums[j];

                if (base == goal) ans++;
            }
        }

        return ans;
    }
}