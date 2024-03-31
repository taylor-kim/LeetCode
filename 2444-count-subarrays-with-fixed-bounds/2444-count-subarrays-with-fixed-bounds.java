class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        return others(nums, minK, maxK);
    }

    public long others(int[] nums, int minK, int maxK) {
        int n = nums.length;
        long ans = 0;
        int bad = -1;
        int min = -1;
        int max = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] < minK || nums[i] > maxK) bad = i;
            if (nums[i] == minK) min = i;
            if (nums[i] == maxK) max = i;

            ans += Math.max(0l, Math.min(min, max) - bad);
        }

        return ans;
    }
}