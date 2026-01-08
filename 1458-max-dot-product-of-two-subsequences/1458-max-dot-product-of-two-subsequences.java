class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        return past_sol(nums1, nums2);
    }

    public int past_sol(int[] nums1, int[] nums2) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int num1 : nums1) {
            min1 = Math.min(min1, num1);
            max1 = Math.max(max1, num1);
        }

        for (int num2 : nums2) {
            min2 = Math.min(min2, num2);
            max2 = Math.max(max2, num2);
        }

        if (max1 < 0 && min2 > 0) {
            return max1 * min2;
        }

        if (max2 < 0 && min1 > 0) {
            return max2 * min1;
        }
        
        return topdown2(nums1, nums2, 0, 0, new Integer[nums1.length][nums2.length]);
    }

    public int topdown2(int[] nums1, int[] nums2, int i1, int i2, Integer[][] memo) {
        if (i1 >= nums1.length || i2 >= nums2.length) return 0;

        if (memo[i1][i2] != null) return memo[i1][i2];

        int include = nums1[i1] * nums2[i2] + topdown2(nums1, nums2, i1 + 1, i2 + 1, memo);
        int exclude1 = topdown2(nums1, nums2, i1 + 1, i2, memo);
        int exclude2 = topdown2(nums1, nums2, i1, i2 + 1, memo);

        return memo[i1][i2] = Math.max(include, Math.max(exclude1, exclude2));
    }

    public int mySol_fail(int[] nums1, int[] nums2) {
        return topdown(nums1, nums2, 0, 0, 0, new Integer[nums1.length][nums2.length]);
    }

    public int topdown(int[] nums1, int[] nums2, int i1, int i2, int length, Integer[][] memo) {
        if (i1 >= nums1.length || i2 >= nums2.length) {
            if (length == 0) return Integer.MIN_VALUE;

            return 0;
        }

        if (memo[i1][i2] != null) return memo[i1][i2];

        int include = nums1[i1] * nums2[i2] + topdown(nums1, nums2, i1 + 1, i2 + 1, length + 1, memo);
        int exclude1 = topdown(nums1, nums2, i1 + 1, i2, length, memo);
        int exclude2 = topdown(nums1, nums2, i1, i2 + 1, length, memo);

        return memo[i1][i2] = Math.max(include, Math.max(exclude1, exclude2));
    }
}