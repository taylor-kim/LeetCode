class Solution {
    public int countValidSelections(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int count = 0;

        for (int num : nums) count += num;

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) continue;
            
            ans += topdown(nums, count, i, 1);
            ans += topdown(nums, count, i, -1);
        }

        return ans;
    }

    private int topdown(int[] nums, int count, int index, int direction) {
        if (count == 0) return 1;

        if (index < 0 || index >= nums.length) return 0;

        if (nums[index] == 0) {
            return topdown(nums, count, index + direction, direction);
        } else {
            nums[index]--;

            int ret = topdown(nums, count - 1, index - direction, -direction);

            nums[index]++;

            return ret;
        }
    }
}