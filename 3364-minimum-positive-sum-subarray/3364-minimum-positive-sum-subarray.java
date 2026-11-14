class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        return mySol(nums, l, r);
    }

    public int mySol(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int ans = Integer.MAX_VALUE;

        for (int length = l; length <= r; length++) {
            int sum = 0;
            int left = 0;

            for (int right = 0; right < n; right++) {
                sum += nums.get(right);

                if (right - left + 1 > length) {
                    sum -= nums.get(left++);
                }

                if (right - left + 1 == length && sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}