class Solution {
    public int countSubarrays(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = 0;
        
        for (int i = 0; i < nums.length - 2; i++) {
            int one = nums[i];
            int two = nums[i + 1];
            int three = nums[i + 2];

            if (two % 2 == 1) continue;

            int halfOfSecond = two / 2;

            if (one + three == halfOfSecond) ans++;
        }

        return ans;
    }
}