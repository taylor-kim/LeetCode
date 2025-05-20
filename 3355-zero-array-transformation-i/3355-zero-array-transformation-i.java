class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        return mySol(nums, queries);
    }

    public boolean mySol(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] arr = new int[n + 1];

        for (int[] query : queries) {
            arr[query[0]]++;
            arr[query[1] + 1]--;
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            nums[i] -= sum;

            if (nums[i] > 0) return false;
        }

        return true;
    }
}