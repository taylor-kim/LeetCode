class Solution {
    public long countBadPairs(int[] nums) {
        return mySol_after_hint(nums);
    }

    public long mySol_after_hint(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < n; i++) {
            int key = nums[i] - i;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        long ans = 0;

        for (int count : map.values()) {
            n -= count;

            ans += count * n;
        }

        return ans;
    }

    public long mySol_fail(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        // 0,1,2,3,4,5,6,7
        // 1,2,4,4,6,7,7,10

        // 1,1,2,1,2,2,1,3
        // {1=4},{2=3},{3=1}

        // i < j
        // nums[j] - nums[i] == j - i
        // then dp[i] = dp[j];
        // else

        return dp[n];
    }
}