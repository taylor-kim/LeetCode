class Solution {
    public long continuousSubarrays(int[] nums) {
        return mySol(nums);
    }

    public long mySol(int[] nums) {
        long ans = 0;
        int left = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            min = Math.min(num, min);
            max = Math.max(num, max);

            while (Math.abs(num - min) > 2 || Math.abs(num - max) > 2) {
                int[] minMax = getMinMax(nums, ++left, right);
                min = minMax[0];
                max = minMax[1];
            }

            ans += (right - left) + 1;

            // System.out.println(String.format("left:%d, right:%d, dp:%d, ans:%d", left, right, dp, ans));
        }

        return ans;
    }

    private int[] getMinMax(int[] nums, int left, int right) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = left; i <= right; i++) {
            int num = nums[i];
            min = Math.min(num, min);
            max = Math.max(num, max);
        }

        return new int[] {min, max};
    }
}