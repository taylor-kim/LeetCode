class Solution {
    public int longestNiceSubarray(int[] nums) {
        return mySol(nums);
    }

    public int official_sw(int[] nums) {
        int left = 0;
        int sum = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            while (left < right && (sum & nums[right]) != 0) {
                sum ^= nums[left++];
            }

            sum |= nums[right];

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }

    public int mySol(int[] nums) {
        // 3, 8, 48
        // 00000000011 : 3
        // 00000000100 : 4
        // 00000001000 : 8
        // 00000110000 : 48
        // 00000110100 : 52

        int n = nums.length;

        int ans = 0;
        int left = 0;
        int sum = 0;
        int[] bits = new int[32];

        for (int right = 0; right < n; right++) {
            int num = nums[right];
            
            while (left < right && (sum & num) != 0) {
                //sum 에서 nums[left++] unset
                int leftNum = nums[left++];
                unsetBits(leftNum, bits);

                for (int i = 0; i < bits.length; i++) {
                    if (bits[i] == 0) {
                        int mask = ~(1 << i);
                        sum &= mask;
                    }
                }
            }

            if ((sum & num) == 0) {
                ans = Math.max(ans, right - left + 1);
            }

            sum |= num;
            setBits(num, bits);
        }

        return ans;
    }

    private void setBits(int num, int[] bits) {
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;

            if ((num & mask) != 0) {
                bits[i]++;

                if (bits[i] > 1) throw new RuntimeException("bits[i] set more than 1 time.");
            }
        }
    }

    private void unsetBits(int num, int[] bits) {
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;

            if ((num & mask) != 0) {
                bits[i]--;
            }
        }
    }
}