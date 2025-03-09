class Solution {
    public long countSubarrays(int[] nums, int k) {
        return others(nums, k);
    }

    public long others(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> prevCounter = new HashMap();

        long ans = 0;

        /**
            target : 111
            1111, 11 : x
            1111, 11111 ..
            1111, 111 : 1ea
            1111, 111, 111 : 3ea
         */

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            Map<Integer, Integer> currentCounter = new HashMap();

            if (isCandidate(num, k)) {
                prevCounter.put(num, prevCounter.getOrDefault(num, 0) + 1);

                for (int cand : prevCounter.keySet()) {
                    int andValue = cand & num;
                    currentCounter.put(andValue, currentCounter.getOrDefault(andValue, 0) + prevCounter.getOrDefault(cand, 0));
                }

                ans += currentCounter.getOrDefault(k, 0);
            }

            prevCounter = currentCounter;
        }

        return ans;
    }

    private boolean isCandidate(int num, int k) {
        return (num & k) == k;
    }
}