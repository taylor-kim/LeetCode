class Solution {
    public int findDuplicate(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int slow = nums[0];
        int fast = slow;

        // 1 -> 3 -> 2 -> 4 -> 2
        // 1 -> 3 -> 2 <-> 4

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];

            if (slow == fast) break;
        }

        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}