class Solution {
    public int minDifference(int[] nums) {
        return try_bottomup(nums);
    }

    public int try_bottomup(int[] nums) {
        Arrays.sort(nums);
        return try_bottomup(nums, 0, nums.length - 1, 3);
    }

    public int try_bottomup(int[] nums, int left, int right, int n) {
        if (left >= right || n == 0) {
            return nums[right] - nums[left];
        }

        int l = try_bottomup(nums, left + 1, right, n - 1);
        int r = try_bottomup(nums, left, right - 1, n - 1);

        return Math.min(l, r);
    }

    public int mySol_fail(int[] nums) {
        int ans = Integer.MAX_VALUE;
        
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;

        int n = 3;

        System.out.println(Arrays.toString(nums));

        while (left <= right && n-- > 0) {
            int mid = left + (right - left) / 2;

            System.out.println(String.format("left:%d, right:%d, mid:%d, ld:%d, rd:%d", left, right, mid, nums[mid] - nums[left], nums[right] - nums[mid]));

            if (nums[mid] - nums[left] < nums[right] - nums[mid]) {
                right--;
            } else {
                left++;
            }
        }

        return left >= right ? 0 : nums[right] - nums[left];
    }
}