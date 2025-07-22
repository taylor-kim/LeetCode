class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int ans = 0;
        int left = 0;
        int sum = 0;

        int[] counter = new int[(int)1e4 + 1];

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            sum += num;
            counter[num]++;

            while (counter[num] > 1) {
                int remove = nums[left++];
                sum -= remove;
                counter[remove]--;
            }

            ans = Math.max(ans, sum);
        }

        return ans;
    }
}