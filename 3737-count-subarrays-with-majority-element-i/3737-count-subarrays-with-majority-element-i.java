class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        return mySol(nums, target);
    }

    public int mySol(int[] nums, int target) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int majorCount = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == target) {
                    majorCount++;
                }

                int length = j - i + 1;

                if (length - majorCount < majorCount) {
                    ans++;
                }
            }
        }

        return ans;
    }
}