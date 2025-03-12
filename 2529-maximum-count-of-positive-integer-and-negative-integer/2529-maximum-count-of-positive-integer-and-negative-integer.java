class Solution {
    public int maximumCount(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;

        int neg = leftmost(nums, 0);
        int pos = leftmost(nums, 1);

        // System.out.println(String.format("neg:%d, pos:%d, n:%d", neg, pos, n));

        return Math.max(neg, n - pos);
    }

    private int leftmost(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}