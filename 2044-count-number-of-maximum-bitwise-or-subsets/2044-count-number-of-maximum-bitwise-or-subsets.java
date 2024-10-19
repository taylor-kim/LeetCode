class Solution {
    public int countMaxOrSubsets(int[] nums) {
        return official_bit(nums);
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