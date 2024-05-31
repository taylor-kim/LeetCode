class Solution {
    public int[] singleNumber(int[] nums) {
        return others2(nums);
    }

    public int[] mySol(int[] nums) {
        return null;
    }

    public int[] others2(int[] nums) {
        int xor = 0;

        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }

        // int bit = xor & (~(xor) + 1);
        int bit = getRightMost(xor);

        int a = 0;

        for (int num : nums) {
            if ((num & bit) != 0) {
                a ^= num;
            }
        }

        return new int[] {a, a ^ xor};
    }

    public int[] others(int[] nums) {
        int[] ans = new int[2];
        int xor = 0;

        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }

        int bit = xor & -xor;

        for (int num : nums) {
            if ((num & bit) != 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }

        return ans;
    }

    private int getLeftMost(int num) {
        int pos = 0;

        while (num > 0) {
            num >>= 1;
            pos++;
        }

        return pos;
    }

    private int getRightMost(int num) {
        int bit = 1;

        while ((num & bit) == 0) {
            bit <<= 1;
        }

        return bit;
    }
}