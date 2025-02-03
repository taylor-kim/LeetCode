class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int incMax = 1;
        int decMax = 1;

        int inc = 1;
        int dec = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                inc++;
                dec = 1;
            } else if (nums[i - 1] > nums[i]) {
                dec++;
                inc = 1;
            } else {
                inc = 1;
                dec = 1;
            }

            incMax = Math.max(incMax, inc);
            decMax = Math.max(decMax, dec);
        }

        return Math.max(incMax, decMax);
    }
}