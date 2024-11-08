class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        return mySol(nums, maximumBit);
    }

    public int[] mySol(int[] nums, int maximumBit) {
        int n = nums.length;
        int xor = 0;
        int max = (int)Math.pow(2, maximumBit);

        for (int num : nums) xor ^= num;

        int[] ans = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int mask = max - 1;

            ans[n - 1 - i] = (~xor) & mask;

            xor ^= nums[i];
        }

        return ans;
    }
}