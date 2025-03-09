class Solution {
    public long countSubarrays(int[] nums, int k) {
        return mySol(nums, k);
    }

    public long mySol(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> counter = new HashMap();

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            Map<Integer, Integer> nextCounter = new HashMap();

            if (isCandidate(num, k)) {
                counter.put(num, counter.getOrDefault(num, 0) + 1);

                for (int cand : counter.keySet()) {
                    nextCounter.put(cand & num, nextCounter.getOrDefault(cand & num, 0) + counter.get(cand));
                }

                ans += nextCounter.getOrDefault(k, 0);
            }

            counter = nextCounter;
        }

        return ans;
    }

    private boolean isCandidate(int num, int k) {
        return (num & k) == k;
    }
}