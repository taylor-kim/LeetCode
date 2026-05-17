class Solution {
    public int findDuplicate(int[] nums) {
        return try_20260517(nums);
    }
    
    public int try_20260517(int[] nums) {
        int n = nums.length;

        int lo = 1;
        int hi = n - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            int count = 0;

            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }

            if (count <= mid) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }
}