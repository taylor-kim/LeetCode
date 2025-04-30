class Solution {
    public int findNumbers(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = 0;

        for (int num : nums) {
            boolean even = true;

            while (num > 0) {
                num /= 10;
                even = !even;
            }

            if (even) ans++;
        }

        return ans;
    }
}