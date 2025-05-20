class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        return try_20250520(nums, queries);
    }

    public int try_20250520(int[] nums, int[][] queries) {
        int lo = 0;
        int hi = queries.length + 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(nums, queries, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo > queries.length ? - 1 : lo;
    }

    private boolean check(int[] nums, int[][] queries, int k) {
        int[] diffArray = new int[nums.length + 1];

        for (int i = 0; i < k; i++) {
            diffArray[queries[i][0]] += queries[i][2];
            diffArray[queries[i][1] + 1] -= queries[i][2];
        }

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += diffArray[i];

            if (nums[i] - sum > 0) return false;
        }

        return true;
    }
}