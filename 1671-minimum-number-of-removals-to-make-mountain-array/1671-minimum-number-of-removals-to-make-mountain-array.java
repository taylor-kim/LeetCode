class Solution {
    public int minimumMountainRemovals(int[] nums) {
        return mySol2(nums);
    }

    public int mySol2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] dp2 = new int[n];

        int ans = n - 3;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            dp2[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i]) {
                    dp2[i] = Math.max(dp2[i], dp2[j] + 1);
                }
            }
        }

        // System.out.println(Arrays.toString(dp));
        // System.out.println(Arrays.toString(dp2));

        for (int i = 1; i < n - 1; i++) {
            // if (dp[i - 1] == dp[i] || dp2[i] == dp2[i + 1]) continue;
            if (dp[i] == 1 || dp2[i] == 1) continue;

            int removeLeft = i + 1 - dp[i];
            int removeRight = n - i - dp2[i];

            ans = Math.min(ans, removeLeft + removeRight);
        }

        return ans;
    }

    public int mySol_fail(int[] nums) {
        int highest = 1;

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[highest]) {
                highest = i;
            }
        }

        // System.out.println(String.format("highest:%d", highest));

        int ans = nums.length;

        int[] dp = new int[nums.length];

        for (int i = 0; i <= highest; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }

        // System.out.println(Arrays.toString(dp));

        ans = (highest + 1 - dp[highest]);

        for (int i = highest; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = highest; j < i; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }

        // System.out.println(Arrays.toString(dp));

        return ans + (nums.length - highest - dp[nums.length - 1]);
    }
}