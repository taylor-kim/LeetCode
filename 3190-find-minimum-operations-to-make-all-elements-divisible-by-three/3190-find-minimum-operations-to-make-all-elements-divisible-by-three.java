class Solution {
    public int minimumOperations(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = 0;

        for (int num : nums) {
            if (num % 3 != 0) ans++;
        }

        return ans;
    }
}