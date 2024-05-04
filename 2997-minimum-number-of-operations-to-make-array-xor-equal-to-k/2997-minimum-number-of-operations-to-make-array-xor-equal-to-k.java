class Solution {
    public int minOperations(int[] nums, int k) {
        return official_two(nums, k);
    }

    public int official_two(int[] nums, int k) {
        int xor = 0;

        for (int num : nums) xor ^= num;

        return Integer.bitCount(xor ^ k);
    }

    public int official_one(int[] nums, int k) {
        int count = 0;
        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        while (k > 0 || xor > 0) {
            if (k % 2 != xor % 2) {
                count++;
            }
            k /= 2;
            xor /= 2;
        }

        return count;
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