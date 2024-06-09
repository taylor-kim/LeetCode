class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);

        int mod = 0;
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            mod = (mod + nums[i]) % k;

            if (mod < 0) mod += k;

            ans += map.getOrDefault(mod, 0);

            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }

        return ans;
    }
}