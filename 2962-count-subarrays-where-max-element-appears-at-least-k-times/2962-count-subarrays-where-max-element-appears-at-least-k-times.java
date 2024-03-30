class Solution {
    public long countSubarrays(int[] nums, int k) {
        return try_again(nums, k);
    }

    public long try_again(int[] nums, int k) {
        int n = nums.length;
        int max = 0;

        for (int num : nums) max = Math.max(max, num);

        int left = 0;
        int count = 0;
        long ans = 0;

        for (int right = 0; right < n; right++) {
            count += nums[right] == max ? 1 : 0;

            while (k == count) {
                if (nums[left++] == max) {
                    count--;
                }
            }
            ans += left;
        }

        return ans;
    }

    public long mySol_misunderstood(int[] nums, int k) {
        int n = nums.length;

        TreeMap<Integer, Integer> map = new TreeMap();

        int left = 0;
        int ans = 0;

        int[] max = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int right = 0; right < n; right++) {
            int rv = nums[right];
            map.put(rv, map.getOrDefault(rv, 0) + 1);

            int local = 0;

            max[right + 1] = Math.max(max[right], rv);

            while (left <= right && map.get(map.lastKey()) >= k) {
                System.out.println(String.format("left:%d, right:%d", left, right));
                local++;
                int lv = nums[left++];
                map.put(lv, map.get(lv) - 1);

                if (map.get(lv) == 0) {
                    map.remove(lv);
                }
            }

            if (max[right + 1] == max[right]) {
                dp[right + 1] += dp[right];
            }

            dp[right + 1] += local;
        }

        for (int num : dp) {
            ans += num;
        }

        return ans;
    }
}