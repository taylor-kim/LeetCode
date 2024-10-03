class Solution {
    public int minSubarray(int[] nums, int p) {
        return official_prefixsum(nums, p);
    }

    public int official_prefixsum(int[] nums, int p) {
        int sum = 0;
        int current = 0;

        for (int num : nums) sum = (sum + num) % p;

        int target = sum % p;

        if (target == 0) return 0;

        Map<Integer, Integer> pSum = new HashMap();
        pSum.put(0, -1);
        int ans = nums.length;

        for (int i = 0; i < nums.length; i++) {
            current = (current + nums[i]) % p;

            int need = (current - target + p) % p;

            if (pSum.containsKey(need)) {
                ans = Math.min(ans, i - pSum.get(need));
            }

            pSum.put(current, i);
        }

        return ans == nums.length ? -1 : ans;
    }

    public int mySol_fail(int[] nums, int p) {
        int sum = 0;
        int current = 0;

        for (int num : nums) sum += (num % p);

        if (sum % p == 0) return 0;

        int ans = Integer.MAX_VALUE;

        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            current += (nums[right] % p);

            while (left < right && current > p) {
                current -= (nums[left++] % p);
            }

            if ((sum - current) % p == 0) {
                ans = Math.min(ans, right - left + 1);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}