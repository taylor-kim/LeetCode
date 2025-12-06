class Solution {
    public int countPartitions(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        return topdown(nums, k, 0, new Integer[nums.length]);
    }

    public int topdown(int[] nums, int k, int index, Integer[] memo) {
        if (index >= nums.length) return 1;

        if (memo[index] != null) return memo[index];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int ans = 0;
        int mod = (int)1e9 + 7;

        for (int i = index; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);

            if (max - min <= k) {
                ans = (ans + topdown(nums, k, i + 1, memo)) % mod;
            } else {
                break;
            }
        }

        return memo[index] = ans;
    }
}