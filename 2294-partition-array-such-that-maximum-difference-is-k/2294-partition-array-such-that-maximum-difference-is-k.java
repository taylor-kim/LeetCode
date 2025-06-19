class Solution {
    public int partitionArray(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        int left = 0;
        int ans = 0;
        
        for (int right = 0; right < n; right++) {
            if (nums[right] - nums[left] > k) {
                left = right;
                ans++;
            }
        }

        ans++;

        return ans;
    }
}