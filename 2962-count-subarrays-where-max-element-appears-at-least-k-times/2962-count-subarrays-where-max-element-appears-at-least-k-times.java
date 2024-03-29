class Solution {
    public long countSubarrays(int[] nums, int k) {
        int maxElement = Arrays.stream(nums).max().getAsInt();
        List<Integer> indexesOfMaxElements = new ArrayList<>();
        long ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == maxElement) {
                indexesOfMaxElements.add(i);
            }

            int freq = indexesOfMaxElements.size();
            if (freq >= k) {
                ans += indexesOfMaxElements.get(freq - k) + 1;
            }
        }

        return ans;
    }
}