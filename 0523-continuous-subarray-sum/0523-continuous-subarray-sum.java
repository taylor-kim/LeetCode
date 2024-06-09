class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        return mySol(nums, k);
    }

    public boolean mySol(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        map.put(0, -1);

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % k;

            if (map.containsKey(sum)) {
                int before = map.getOrDefault(sum, -1);

                if (i - before > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }

        return false;
    }
}