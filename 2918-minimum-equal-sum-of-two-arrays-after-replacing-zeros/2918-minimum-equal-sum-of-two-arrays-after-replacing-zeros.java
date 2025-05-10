class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        return hint1(nums1, nums2);
    }

    public long hint1(int[] nums1, int[] nums2) {
        long sum1 = 0;
        int zero1 = 0;

        long sum2 = 0;
        int zero2 = 0;

        for (int num : nums1) {
            sum1 += num;
            if (num == 0) zero1++;
        }

        for (int num : nums2) {
            sum2 += num;
            if (num == 0) zero2++;
        }

        sum1 += zero1;
        sum2 += zero2;

        if (sum1 < sum2 && zero1 == 0) return -1;

        if (sum1 > sum2 && zero2 == 0) return -1;

        return Math.max(sum1, sum2);
    }

    public long mySol_fail(int[] nums1, int[] nums2) {
        int sum1 = 0;
        int zero1 = 0;

        int sum2 = 0;
        int zero2 = 0;

        for (int num : nums1) {
            sum1 += num;
            if (num == 0) zero1++;
        }

        for (int num : nums2) {
            sum2 += num;
            if (num == 0) zero2++;
        }

        if (sum1 < sum2) {
            sum2 += zero2;

            if (zero1 > 0 && zero2 > 0 && sum1 + zero1 <= sum2) return sum2;
        } else {
            sum1 += zero1;

            if (zero2 > 0 && zero1 > 0 && sum2 + zero2 <= sum1) return sum1;
        }

        return -1;
    }
}