class Solution {
    public int minOperations(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        Arrays.sort(nums);

        if (nums[0] < k) return -1;

        int count = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            int current = nums[i];
            int next = nums[i + 1];

            if (current != next) {
                count++;
            }
        }

        return count - (nums[0] == k ? 1 : 0);
    }
}