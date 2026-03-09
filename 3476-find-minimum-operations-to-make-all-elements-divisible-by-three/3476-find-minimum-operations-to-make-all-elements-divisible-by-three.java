class Solution {
    public int minimumOperations(int[] nums) {
        return Arrays.stream(nums)
                .map(x -> Math.min(x % 3, 3 - x % 3))
                .sum();
    }
}