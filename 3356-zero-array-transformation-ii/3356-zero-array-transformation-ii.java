class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        return official_diff_array(nums, queries);
    }

    public int official_diff_array(int[] nums, int[][] queries) {
        int n = nums.length;

        int lo = 0;
        int hi = queries.length + 1;

        int ans = -1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (currentIndexZero(nums, queries, mid)) {
                ans = mid;
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private boolean currentIndexZero(int[] nums, int[][] queries, int k) {
        int[] diffArray = new int[nums.length + 1];

        for (int i = 0; i < k; i++) {
            int[] query = queries[i];
            diffArray[query[0]] += query[2];
            diffArray[query[1] + 1] -= query[2];
        }

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += diffArray[i];

            if (sum < nums[i]) return false;
        }

        return true;
    }

    public int mySol(int[] nums, int[][] queries) {
        int n = nums.length;

        int lo = 0;
        int hi = queries.length + 1;
        int ans = -1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            int[] diffArray = getDiffArray(queries, n, mid);

            if (isZero(nums, diffArray)) {
                ans = mid;
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private int[] getDiffArray(int[][] queries, int n, int k) {
        int[] diffArray = new int[n + 1];

        for (int i = 0; i < k; i++) {
            int[] query = queries[i];
            diffArray[query[0]] += query[2];
            diffArray[query[1] + 1] -= query[2];
        }

        for (int i = 0; i < n; i++) {
            diffArray[i + 1] += diffArray[i];
        }

        return diffArray;
    }

    private boolean isZero(int[] nums, int[] diffArray) {
        int remain = 0;

        for (int i = 0; i < nums.length; i++) {
            remain += Math.max(nums[i] - diffArray[i], 0);
        }

        return remain == 0;
    }
}