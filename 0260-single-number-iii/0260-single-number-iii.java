class Solution {
    public int[] singleNumber(int[] nums) {
        return try_20240601(nums);
    }

    public int[] try_20240601(int[] nums) {
        int xor = 0;

        for (int num : nums) xor ^= num;

        xor = findLeftmost(xor);
        // xor = xor & (-xor);

        int[] ans = new int[2];

        for (int num : nums) {
            if ((xor & num) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }

        return ans;
    }

    private int findLeftmost(int num) {
        int pos = 0;

        while (pos < 32) {
            int mask = 1 << pos;

            if ((mask & num) != 0) {
                return mask;
            }

            pos++;
        }

        return -1;
    }

    private int findRightmost(int num) {
        int pos = 31;

        while (pos >= 0) {
            int mask = 1 << pos;

            if ((mask & num) != 0) {
                return mask;
            }

            pos--;
        }

        return -1;
    }
}