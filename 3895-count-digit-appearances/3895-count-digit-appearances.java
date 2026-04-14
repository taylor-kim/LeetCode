class Solution {
    public int countDigitOccurrences(int[] nums, int digit) {
        return mySol(nums, digit);
    }

    public int mySol(int[] nums, int digit) {
        int ans = 0;

        for (int num : nums) {
            while (num > 0) {
                if (num % 10 == digit) ans++;

                num /= 10;
            }
        }

        return ans;
    }
}