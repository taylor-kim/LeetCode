class Solution {
    public int findPeakElement(int[] nums) {
        return official1(nums);
    }

    public int official1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }

        return nums.length - 1;
    }

    public int official2(int[] nums) {
        return official2(nums, 0, nums.length - 1);
    }

    public int official2(int[] nums, int lo, int hi) {
        if (lo == hi) return lo;

        int mid = lo + (hi - lo) / 2;

        if (nums[mid] > nums[mid + 1]) {
            return official2(nums, lo, mid);
        } else {
            return official2(nums, mid + 1, hi);
        }
    }

    public int official3(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    /***
    terrible problem.
    I misunderstood this question, return the highest peak.
    WTF
     */

    public int afterUnderstandICanReturnAnyOfPeak(int[] nums) {
        if (nums.length == 1) return 0;

        int n = nums.length;

        if (nums[0] > nums[1]) return 0;
        if (nums[n - 1] > nums[n - 2]) return n - 1;

        int lo = 1;
        int hi = n - 2;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid - 1] < nums[mid]) {
                lo = mid + 1;
            } else if (nums[mid - 1] > nums[mid]) {
                hi = mid - 1;
            }
        }

        return -1;
    }

    public int mySol(int[] nums) {
        int ans = 0;

        for (int i = 1; i < nums.length; i++) {
            ans = nums[ans] > nums[i] ? ans : i;
        }

        return ans;
    }
}