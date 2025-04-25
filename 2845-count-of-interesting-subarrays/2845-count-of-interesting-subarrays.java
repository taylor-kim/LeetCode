class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        return official_psum(nums, modulo, k);
    }

    public long official_psum(List<Integer> nums, int modulo, int k) {
        long ans = 0;

        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);

        int count = 0;

        for (int num : nums) {
            count += num % modulo == k ? 1 : 0;

            ans += map.getOrDefault((count - k + modulo) % modulo, 0);

            map.put(count % modulo, map.getOrDefault(count % modulo, 0) + 1);
        }

        return ans;
    }

    public long try_official_psum_fail(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            pSum[i + 1] = pSum[i] + (num % modulo == k ? 1 : 0);
        }

        System.out.println(Arrays.toString(pSum));

        int left = 0;
        long ans = 0;

        for (int right = 0; right < n; right++) {
            while (left <= right && (pSum[right + 1] - pSum[left]) % modulo == k) {
                ans++;
                left++;
            }
        }

        return ans;
    }

    public long mySol_fail(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        long ans = 0;

        int left = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            nums.set(i, nums.get(i) % modulo);
        }

        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1] + nums.get(i) == k ? 1 : 0;
        }

        System.out.println(Arrays.toString(dp));
        
        for (int right = 0; right < n; right++) {
            int num = nums.get(right);

            ans += dp[right] / modulo + (dp[right] % modulo == k ? 1 : 0);
        }

        // count % mod == k

        // mod = 3, k = 2
        // [l..] => count : 5
        // num[l] % mod == k -> ans += 1, 4, 7, 10, 13... => (count - k) / mod + 1
        // num[l] % mod == k -> ans += 2, 5, 8, 11, 14... => (count - k) / mod + 1
        // num[l] % mod != k -> ans += 

        // [1,0,1,1]
        // [1,1,2,3]

        // [3,1,1,9,6]
        // [0,1,1,0,0]
        // [1,0,0,1,1]
        // [1,1,1,2,3]

        return ans;
    }
}