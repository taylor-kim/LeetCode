class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        int ans = 0;
        int max = 0;
        Map<Integer, Integer> freq = new HashMap();
        Map<Integer, List<Integer>> indices = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            indices.computeIfAbsent(num, key -> new ArrayList()).add(i);
        }

        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            int rv = nums[right];
            int nextCount = freq.getOrDefault(rv, 0) + 1;
            freq.put(rv, nextCount);

            max = Math.max(max, nextCount);

            if (max > k) {
                int leftestIndex = indices.get(rv).get(0);

                while (left <= leftestIndex) {
                    int lv = nums[left];
                    freq.put(lv, freq.get(lv) - 1);
                    if (indices.get(lv).size() > 0) {
                        indices.get(lv).remove(0);
                    }
                    left++;
                }
                max--;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}