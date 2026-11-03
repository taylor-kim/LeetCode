class Solution {
    public int minMoves(int[] nums) {
        return others(nums);
    }

    public int others(int[] nums) {
        int n = nums.length;
        int max = 0;
        int sum = 0;

        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        return max * n - sum;
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans += nums[n - 1] - nums[i];
        }

        return ans;
    }
}