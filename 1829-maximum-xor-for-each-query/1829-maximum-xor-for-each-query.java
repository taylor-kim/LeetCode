class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        return mySol(nums, maximumBit);
    }

    public int[] mySol(int[] nums, int maximumBit) {
        int n = nums.length;
        int xor = 0;
        int mask = (int)Math.pow(2, maximumBit) - 1;

        for (int num : nums) xor ^= num;

        int[] ans = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            // ans[n - 1 - i] = (~xor) & mask;
            ans[n - 1 - i] = xor ^ mask;

            xor ^= nums[i];
        }

        return ans;
    }
}