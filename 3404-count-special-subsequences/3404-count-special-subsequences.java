class Solution {
    public long numberOfSubsequences(int[] nums) {
        return mySol(nums);
    }

    public long mySol(int[] nums) {
        int n = nums.length;
        
        Map<Double, Integer> ratioCount = new HashMap<>();

        long ans = 0;

        for (int r = 4; r < n - 2; r++) {
            int q = r - 2;

            for (int p = 0; p < q - 1; p++) {
                double ratio = 1.0 * nums[p] / nums[q];
                ratioCount.put(ratio, ratioCount.getOrDefault(ratio, 0) + 1);
            }

            for (int s = r + 2; s < n; s++) {
                double ratio = 1.0 * nums[s] / nums[r];
                ans += ratioCount.getOrDefault(ratio, 0);
            }
        }

        return ans;
    }
}
