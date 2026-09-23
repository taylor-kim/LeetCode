class Solution {
    public int minOperations(int[] nums, int x) {
        return mySol(nums, x);
    }

    public int mySol(int[] nums, int x) {
        Map<Integer, Integer> pSum = new HashMap();

        int n = nums.length;
        int sum = 0;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            if (sum == x) {
                ans = Math.min(ans, i + 1);
            }

            pSum.put(sum, i);
        }

        sum = 0;

        for (int i = n - 1; i >= 0; i--) {
            sum += nums[i];

            if (sum == x) {
                ans = Math.min(ans, n - i);
            }

            if (pSum.containsKey(x - sum) && pSum.get(x - sum) < i) {
                ans = Math.min(ans, n - i + pSum.get(x - sum) + 1);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}