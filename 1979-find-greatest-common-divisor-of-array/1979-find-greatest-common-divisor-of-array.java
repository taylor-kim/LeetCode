class Solution {
    public int findGCD(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        while (min > 0) {
            max %= min;
            int temp = min;
            min = max;
            max = temp;
        }

        return max;
    }
}