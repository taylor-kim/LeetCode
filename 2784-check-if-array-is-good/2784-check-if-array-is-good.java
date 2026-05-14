class Solution {
    public boolean isGood(int[] nums) {
        return mySol(nums);
    }

    public boolean mySol(int[] nums) {
        int n = nums.length;
        int baseN = n - 1;

        int[] base = new int[baseN + 1];

        for (int num : nums) {
            if (num > baseN) return false;

            base[num]++;

            if (num != baseN && base[num] > 1) return false;
        }

        return base[baseN] == 2;
    }
}