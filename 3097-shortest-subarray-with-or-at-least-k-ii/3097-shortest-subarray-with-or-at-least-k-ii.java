class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        Arrays.sort(nums);

        int index = Arrays.binarySearch(nums, k);

        if (index >= 0) return 1;

        index = -(index + 1);

        if (index < nums.length) return 1;

        int ans = 0;
        int or = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            int next = or | nums[i];

            if ((or | nums[i]) > or) {
                or |= nums[i];
                ans++;
            }

            if (or > k) break;
        }

        // System.out.println(String.format("count:%d, or:%d", ans, or));

        return or >= k ? ans : -1;
    }
}