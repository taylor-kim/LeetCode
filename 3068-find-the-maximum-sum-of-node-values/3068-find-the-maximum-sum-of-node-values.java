class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        return official_topdown(nums, k, edges);
    }

    public long official_topdown(int[] nums, int k, int[][] edges) {
        return official_topdown(nums, k, 0, 1, new Long[nums.length][2]);
    }

    public long official_topdown(int[] nums, int k, int index, int isEven, Long[][] memo) {
        if (index >= nums.length) {
            return isEven == 1 ? 0 : Long.MIN_VALUE;
        }

        if (memo[index][isEven] != null) {
            return memo[index][isEven];
        }

        long noXor = nums[index] + official_topdown(nums, k, index + 1, isEven, memo);

        long xor = (nums[index] ^ k) + official_topdown(nums, k, index + 1, isEven ^ 1, memo);

        return memo[index][isEven] = Math.max(noXor, xor);
    }

    public long mySol_fail(int[] nums, int k, int[][] edges) {
        int ans = 0;

        for (int num : nums) {
            ans += Math.max(num, num ^ k);
        }

        return ans;
    }
}