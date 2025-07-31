class Solution {
    public int countMaxOrSubsets(int[] nums) {
        return try_20250731(nums);
    }

    public int try_20250731(int[] nums) {
        int max = 0;

        for (int num : nums) max |= num;

        return topdown(0, 0, nums, max);
    }

    private int topdown(int index, int or, int[] nums, int max) {
        if (index >= nums.length) return 0;

        int ans = 0;

        if ((or | nums[index]) == max) ans++;

        int include = topdown(index + 1, or | nums[index], nums, max);
        int exclude = topdown(index + 1, or, nums, max);

        return ans + include + exclude;
    }






    public int official_bit_dp(int[] nums) {
        int max = 0;

        for (int num : nums) max |= num;

        int leftMost = 0;

        for (int i = 31; i >= 0; i--) {
            if (((max >> i) & 1) == 1) {
                leftMost = i;
                break;
            }
        }

        int size = (1 << (leftMost + 1)) - 1;

        // System.out.println(String.format("max:%d, leftMost:%d, size:%d", max, leftMost, size));

        int[] dp = new int[size + 1];
        dp[0] = 1;
        max = 0;

        for (int num : nums) {
            // System.out.println(String.format("max:%d", max));
            
            for (int i = max; i >= 0; i--) {
                dp[i | num] += dp[i];

                // System.out.println(Arrays.toString(dp));
            }

            max |= num;
        }

        // System.out.println(Arrays.toString(dp));

        return dp[max];
    }

    public int official_bit(int[] nums) {
        int subsetCount = 1 << nums.length;
        int ans = 0;

        int target = 0;

        for (int num : nums) target |= num;

        for (int subset = 0; subset < subsetCount; subset++) {
            int or = 0;

            for (int i = 0; i < nums.length; i++) {
                if (((subset >> i) & 1) == 1) or |= nums[i];
            }

            if (or == target) ans++;
        }

        return ans;
    }

    public int official_topdown_memo(int[] nums) {
        int target = 0;

        for (int num : nums) target |= num;

        return official_topdown_memo(nums, 0, 0, target, new Integer[nums.length][target + 1]);
    }

    public int official_topdown_memo(int[] nums, int index, int or, int target, Integer[][] memo) {
        if (index >= nums.length) return or == target ? 1 : 0;

        if (memo[index][or] != null) return memo[index][or];

        int exclude = official_topdown_memo(nums, index + 1, or, target, memo);
        int include = official_topdown_memo(nums, index + 1, or | nums[index], target, memo);

        return memo[index][or] = exclude + include;
    }

    public int official_topdown(int[] nums) {
        int target = 0;

        for (int num : nums) target |= num;

        return official_topdown(nums, 0, 0, target);
    }

    public int official_topdown(int[] nums, int index, int or, int target) {
        if (index >= nums.length) return or == target ? 1 : 0;

        int exclude = official_topdown(nums, index + 1, or, target);
        int include = official_topdown(nums, index + 1, or | nums[index], target);

        return exclude + include;
    }

    public int my_bf(int[] nums) {
        int[] ans = {0};

        int target = 0;

        for (int num : nums) target |= num;

        topdown(nums, 0, 0, target, ans);

        return ans[0];
    }

    private void topdown(int[] nums, int index, int or, int target, int[] ans) {
        if (index >= nums.length) return;

        int next = or | nums[index];

        if (next == target) ans[0]++;

        topdown(nums, index + 1, or, target, ans);
        topdown(nums, index + 1, next, target, ans);
    }

    public int mySol3_giveup(int[] nums) {
        return 0;
    }

    public int mySol2_fail(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        int max = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] | nums[i];
            max = Math.max(max, dp[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < j; j++) {

            }
        }

        return 0;
    }

    public int mySol_fail(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        int[] orDp = new int[n];
        int[] countDp = new int[n];

        orDp[0] = nums[0];
        countDp[0] = 1;

        int mi = 0;

        System.out.println(Arrays.toString(nums));

        for (int i = 1; i < n; i++) {
            orDp[i] = orDp[i - 1] | nums[i];

            if (orDp[i - 1] < orDp[i] && nums[i] == orDp[i]) {
                countDp[i] = 2 * countDp[i - 1] + 1;
            } else if (orDp[i - 1] < orDp[i]) {
                countDp[i] = 1;
            }

            if (orDp[mi] <= orDp[i]) {
                mi = i;
            }
        }

        System.out.println(Arrays.toString(orDp));

        return countDp[mi];
    }
}