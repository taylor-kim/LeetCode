class Solution {
    public int subarraySum(int[] nums, int k) {
        return try_20240701(nums, k);
    }

    public int try_20240701(int[] nums, int k) {
        int ans = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
}