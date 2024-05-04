class Solution {
    public int minOperations(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        int ans = 0;

        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        xor ^= k;

        for (int i = 0; i < 32; i++) {
            int mask = (1 << i);

            if ((xor & mask) != 0) {
                ans++;
            }
        }

        return ans;
    }
}