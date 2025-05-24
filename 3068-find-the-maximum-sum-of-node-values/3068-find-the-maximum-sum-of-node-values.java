class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        return official_topdown(nums, k, edges);
    }

    public long official_topdown(int[] nums, int k, int[][] edges) {
        return topdown(nums, 0, 0, k, new Long[nums.length][2]);
    }

    private long topdown(int[] nums, int index, int changeCount, int k, Long[][] memo) {
        if (index >= nums.length) {
            return changeCount % 2 == 0 ? 0 : Integer.MIN_VALUE;
        }

        if (memo[index][changeCount % 2] != null) {
            return memo[index][changeCount % 2];
        }

        long xor = (nums[index] ^ k) + topdown(nums, index + 1, changeCount + 1, k, memo);
        long noXor = nums[index] + topdown(nums, index + 1, changeCount, k, memo);

        return memo[index][changeCount % 2] = Math.max(xor, noXor);
    }
}