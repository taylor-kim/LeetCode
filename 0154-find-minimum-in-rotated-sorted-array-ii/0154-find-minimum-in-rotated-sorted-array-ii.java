class Solution {
    public int findMin(int[] nums) {
        return editorial(nums);
    }

    public int editorial(int[] nums) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] == nums[hi]) {
                hi--;
            } else {
                hi = mid;
            }
        }

        return nums[lo];
    }

    public int others_better(int[] nums) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;

        while (lo < hi) {
            while (lo < hi && nums[lo] == nums[hi]) {
                lo++;
            }

            int mid = lo + (hi - lo) / 2;

            if (nums[lo] <= nums[hi]) {
                hi = mid;
            } else {
                if (nums[mid] > nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
        }

        return nums[lo];
    }

    public int mySol_fail(int[] nums) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;

        long[] pSum = new long[n + 1];

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];

            min = Math.min(min, nums[i]);
        }

        return min;

        // System.out.println("pSum:%s".formatted(Arrays.toString(pSum)));

        // while (lo < hi) {
        //     int mid = lo + (hi - lo) / 2;

        //     if (nums[lo] < nums[hi]) {
        //         hi = mid;
        //     } else if (nums[lo] == nums[hi]) {
        //         int leftLength = mid - lo + 1;
        //         int rightLength = hi - mid;

        //         long leftSum = pSum[mid + 1] - pSum[lo];
        //         long rightSum = pSum[hi + 1] - pSum[mid + 1];

        //         if (leftSum == rightSum && leftLength == rightLength) {
        //             break;
        //         } else {
        //             double leftAvg = (double)leftSum / leftLength;
        //             double rightAvg = (double)rightSum / rightLength;

        //             if (leftAvg < rightAvg) {
        //                 hi = mid;
        //             } else {
        //                 lo = mid + 1;
        //             }
        //         }
        //     } else {
        //         if (nums[mid] > nums[hi]) {
        //             lo = mid + 1;
        //         } else {
        //             hi = mid;
        //         }
        //     }
        // }

        // // System.out.println("result index : %d".formatted(lo));

        // return nums[lo];
    }
}