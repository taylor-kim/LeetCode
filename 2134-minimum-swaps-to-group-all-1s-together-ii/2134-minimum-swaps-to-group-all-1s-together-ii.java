class Solution {
    public int minSwaps(int[] nums) {
        return tryAgain(nums);
    }

    public int tryAgain(int[] nums) {
        int count = 0;

        for (int num : nums) if (num == 1) count++;

        if (count == 0) return 0;

        int left = 0;
        int zero = 0;

        int ans = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length + count - 1; right++) {
            zero += 1 - nums[right % nums.length];

            if (right - left + 1 > count) {
                zero -= 1 - nums[left++ % nums.length];
            }

            if (right - left + 1 == count) {
                ans = Math.min(ans, zero);
            }
        }

        return ans;
    }
}