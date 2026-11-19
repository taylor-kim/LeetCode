class Solution {
    public int minimizeSum(int[] nums) {
        return withoutSort(nums);
    }

    public int withoutSort(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num < min1) {
                min3 = min2;
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min3 = min2;
                min2 = num;
            } else if (num < min3) {
                min3 = num;
            }

            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
        }

        return Math.min(max2 - min2, Math.min(max1 - min3, max3 - min1));
    }

    public int mySol(int[] nums) {
        int n = nums.length;

        if (n <= 3) return 0;

        Arrays.sort(nums);

        int sides = nums[n - 2] - nums[1];
        int left = nums[n - 1] - nums[2];
        int right = nums[n - 3] - nums[0];

        return Math.min(sides, Math.min(left, right));
    }
}