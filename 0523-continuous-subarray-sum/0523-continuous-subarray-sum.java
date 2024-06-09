class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        return official(nums, k);
    }

    public boolean official(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        map.put(0, -1);

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % k;

            if (map.containsKey(sum)) {
                int before = map.get(sum);

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