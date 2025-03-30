class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        return mySol(nums, queries);
    }

    public boolean mySol(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diffArray = new int[n + 1];

        for (int i = 0; i < queries.length; i++) {
            diffArray[queries[i][0]]++;
            diffArray[queries[i][1] + 1]--;
        }

        if (diffArray[0] < nums[0]) {
            return false;
        }

        for (int i = 1; i < n; i++) {
            diffArray[i] += diffArray[i - 1];

            if (diffArray[i] < nums[i]) {
                return false;
            }
        }

        return true;
    }
}
