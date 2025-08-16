class Solution {
    public int maximumLength(int[] nums) {
        return try_20250816_2(nums);
    }

    public int try_20250816_2(int[] nums) {
        int zeros = 0;
        int ones = 0;
        int pingPong = 0;
        int prevMod = -1;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int mod = num % 2;

            if (mod == 0) {
                zeros++;
            } else {
                ones++;
            }

            if (prevMod != mod) {
                pingPong++;
                prevMod = mod;
            }
        }

        return Math.max(pingPong, Math.max(zeros, ones));
    }

    public int try_20250816_fail(int[] nums) {
        return Math.max(
            topdown(nums, 0, 0, 0),
            // topdown(nums, 1, 0, 0)
            0
        );
    }

    public int topdown(int[] nums, int mod, int prev, int index) {
        if (index >= nums.length) return 0;

        int ans = 0;

        if (prev == index) {
            int include = 1 + topdown(nums, mod, index, index + 1);
            int exclude = topdown(nums, mod, prev, index + 1);

            ans = Math.max(include, exclude);
        } else if ((nums[prev] + nums[index]) % 2 == mod) {
            ans = 1 + topdown(nums, mod, index, index + 1);
        } else {
            ans = Math.max(ans, topdown(nums, mod, prev, index + 1));
            ans = Math.max(ans, topdown(nums, mod, index, index + 1));
        }

        return ans;
    }
}