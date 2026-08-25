class Solution {
    public int missingMultiple(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        int max = (100 / k) + 1;
        boolean[] exists = new boolean[max + 1];

        for (int num : nums) {
            if (num % k != 0) continue;

            exists[num / k] = true;
        }

        for (int i = 1; i <= max; i++) {
            if (!exists[i]) return i * k;
        }

        return -1;
    }
}