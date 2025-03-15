class Solution {
    public int rob(int[] nums) {
        return tryAgain_20250315(nums);
    }

    public int tryAgain_20250315(int[] nums) {
        int current = 0;
        int prevAdj = 0;
        int prev = 0;
        
        for (int i = 0; i < nums.length; i++) {
            current = Math.max(nums[i] + prev, prevAdj);

            prev = prevAdj;
            prevAdj = current;
        }

        return current;
    }
}