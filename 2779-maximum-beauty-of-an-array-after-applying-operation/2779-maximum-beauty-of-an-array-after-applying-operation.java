class Solution {
    public int maximumBeauty(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        int max = 0;

        for (int num : nums) max = Math.max(max, num);

        int n = max + k;

        int[] freq = new int[n + 1];

        for (int num : nums) {
            freq[num]++;
        }

        int left = 0;
        int sum = 0;
        int ans = 0;
        for (int right = 0; right < freq.length; right++) {
            sum += freq[right];

            while (right - left + 1 > 2 * k + 1) {
                sum -= freq[left++];
            }

            ans = Math.max(ans, sum);
        }

        return ans;
    }
}