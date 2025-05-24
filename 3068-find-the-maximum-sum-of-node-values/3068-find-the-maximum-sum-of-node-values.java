class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        return official_topdown(nums, k, edges);
    }

    public long official_topdown(int[] nums, int k, int[][] edges) {
        return topdown(nums, 0, 1, k, new Long[nums.length][2]);
    }

    private long topdown(int[] nums, int index, int isEven, int k, Long[][] memo) {
        if (index >= nums.length) {
            return isEven == 1 ? 0 : Integer.MIN_VALUE;
        }

        if (memo[index][isEven] != null) {
            return memo[index][isEven];
        }

        long xor = (nums[index] ^ k) + topdown(nums, index + 1, isEven ^ 1, k, memo);
        long noXor = nums[index] + topdown(nums, index + 1, isEven, k, memo);

        return memo[index][isEven] = Math.max(xor, noXor);
    }
}