class Solution {
    public int specialArray(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = -1;

        int left = 0;
        int right = nums.length;

        Arrays.sort(nums);

        while (left <= right) {
            int x = left + (right - left) / 2;

            int posX = leftmost(nums, x);
            int count = 0;

            // if (posX < 0) {
            //     count = nums.length;
            // } else if (posX == nums.length) {
            //     count = 0;
            // } else {
            //     count = nums.length - posX;
            // }

            count = nums.length - posX;

            if (count == x) {
                return x;
            } else if (count < x) {
                right = x - 1;
            } else {
                left = x + 1;
            }
        }

        return ans;
    }

    private int leftmost(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // if (left == 0 && nums[left] != target) return -1;

        return left;
    }
}