class Solution {
    public int minimumDeletions(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int min = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[min]) {
                min = i;
            }

            if (nums[i] > nums[max]) {
                max = i;
            }
        }

        int left = Math.min(min, max);
        int right = Math.max(min, max);

        int side = left + 1 + (n - right);
        int fromLeft = right + 1;
        int fromRight = n - left;

        return Math.min(side, Math.min(fromLeft, fromRight));
    }
}