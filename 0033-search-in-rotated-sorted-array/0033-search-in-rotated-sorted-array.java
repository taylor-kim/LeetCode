class Solution {
    public int search(int[] nums, int target) {
        return tryAgain(nums, target);
    }

    public int tryAgain_20230808(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target <= nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target > nums[mid] && target < nums[lo]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return -1;
    }

    public int tryAgain2(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[lo] <= nums[mid]) {
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
        /***
        
        target : 2

        0,1,2,3,4,5
        1,2,3,4,5,0
        2,3,4,5,0,1
        3,4,5,0,1,2

        4,5,0,1,2,3
        5,0,1,2,3,4

         */
    }

    public int tryAgain(int[] nums, int target) {
        int pivot = findPivot2(nums) + 1;

        int index = Arrays.binarySearch(nums, 0, pivot, target);

        if (index >= 0) {
            return index;
        }

        if (pivot < nums.length) {
            index = Arrays.binarySearch(nums, pivot, nums.length, target);

            return index >= 0 ? index : -1;
        } else {
            return -1;
        }
    }

    public int findPivot2(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (lo == mid) {
                return lo;
            } else if (nums[lo] < nums[mid]) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return -1;
    }

    public int findPivot(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[lo] < nums[mid]) {
                lo = mid;
            } else if (nums[lo] > nums[mid]) {
                hi = mid;
            } else {
                return lo + 1;
            }
        }

        return -1;
    }

    public int improved(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[lo] <= nums[mid]) {
                    if (nums[lo] <= target && target <= nums[mid]) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                } else {
                    if (nums[mid] <= target && target <= nums[hi]) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
        }

        return -1;
    }

    public int mySol(int[] nums, int target) {
        int pivot = findPivot(nums, 0, nums.length - 1);

        int end = pivot != -1 ? pivot + 1 : nums.length;

        int index = Arrays.binarySearch(nums, 0, end, target);

        if (index >= 0) {
            return index;
        }

        if (end < nums.length) {
            index = Arrays.binarySearch(nums, end, nums.length, target);

            return index >= 0 ? index : -1;
        } else {
            return -1;
        }
    }

    private int findPivot(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }

        int mid = lo + (hi - lo) / 2;

        if (mid == nums.length - 1 || nums[mid] > nums[mid + 1]) {
            return mid;
        }

        if (nums[lo] > nums[mid]) {
            return findPivot(nums, lo, mid - 1);
        } else {
            return findPivot(nums, mid + 1, hi);
        }
    }
}