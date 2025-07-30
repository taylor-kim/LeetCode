class Solution {
    public int longestSubarray(int[] nums) {
        return try_20250730(nums);
    }

    public int try_20250730(int[] nums) {
        int n = nums.length;
        int ans = 1;
        int max = nums[0];
        int and = nums[0];

        int left = 0;

        for (int right = 1; right < n; right++) {
            int num = nums[right];

            and &= num;

            if (and < max || max < num) {
                if (max < num) {
                    ans = 1;
                }
                max = Math.max(max, num);
                left = right;
                and = num;
            } else if (and == max) {
                ans = Math.max(ans, right - left + 1);
            }
        }

        return ans;
    }

    private int getNumer(int[] bits) {
        int num = 0;

        for (int i = 0; i < bits.length; i++) {
            int bit = 1 << i;

            if (bits[bit] != 0) {
                num |= bit;
            }
        }

        return num;
    }

    public int official_oneway(int[] nums) {
        int ans = 0;
        int max = 0;
        int count = 0;

        for (int num : nums) {
            if (max < num) {
                max = num;
                ans = 0;
                count = 0;
            }

            if (max == num) {
                count++;
            } else {
                count = 0;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }

    public int mySol(int[] nums) {
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
        }

        int ans = 1;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                count++;
            } else {
                count = 0;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}