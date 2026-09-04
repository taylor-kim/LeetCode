class Solution {
    public int firstStableIndex(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        int n = nums.length;
        int[] min = new int[n];
        int[] max = new int[n];
        min[n - 1] = nums[n - 1];
        max[0] = nums[0];

        for (int i = 1; i < n; i++) {
            max[i] = Math.max(max[i - 1], nums[i]);

            int j = n - 1 - i;
            min[j] = Math.min(min[j + 1], nums[j]);
        }

        int minScore = max[n - 1] + 1;

        for (int i = 0; i < n; i++) {
            int score = max[i] - min[i];

            if (score <= k && score < minScore) {
                minScore = score;
                return i;
            }
        }

        return -1;
    }
}