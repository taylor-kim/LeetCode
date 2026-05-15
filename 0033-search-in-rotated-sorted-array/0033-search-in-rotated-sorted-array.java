class Solution {
    public int search(int[] nums, int target) {
        return try_20260515(nums, target);
    }

    public int try_20260515(int[] nums, int target) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // System.out.println("lo:%d, mid:%d, hi:%d".formatted(lo, mid, hi));

            if (nums[mid] == target) return mid;

            if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return -1;
    }

    public int try_20240410(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[left] <= nums[mid]) {
                    if (nums[left] <= target && target <= nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (nums[mid] < target && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }

        return -1;
    }
}