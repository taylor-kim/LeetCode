class Solution {
    public int longestSubsequence(int[] nums) {
        return tryAgain_20250113(nums);
    }

    public int tryAgain_20250113(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int max = 299;
        int[][] dp = new int[n][max + 1];

        for (int[] row : dp) {
            Arrays.fill(row, 1);
        }

        for (int i = 1; i < n; i++) {
            for (int diff = max - 1; diff >= 0; diff--) {
                dp[i - 1][diff] = Math.max(dp[i - 1][diff], dp[i - 1][diff + 1]);
            }

            for (int j = 0; j < i; j++) {
                int diff = Math.abs(nums[i] - nums[j]);
                dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] + 1);

                ans = Math.max(ans, dp[i][diff]);
            }
        }

        return ans;
    }

    public int others(int[] nums) {
        int n = nums.length;
        int min = 300;
        int max = 0;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int[][] dp = new int[n][max + 1];

        for (int[] arr : dp) {
            Arrays.fill(arr, 1);
        }

        int ans = 0;

        // 1,2,3,4,5
        
        // 2,3,4,5,5
        // 5,5,5,5,5

        for (int i = 1; i < n; i++) {
            // for (int j = max - 1; j >= 0; j--) {
            //     dp[i - 1][j] = Math.max(dp[i - 1][j], dp[i - 1][j + 1]);
            // }

            int currentMax = dp[i - 1][max];

            for (int j = max - 1; j >= 0; j--) {
                currentMax = Math.max(currentMax, dp[i - 1][j]);
                dp[i - 1][j] = currentMax;
            }

            for (int j = 0; j < i; j++) {
                int diff = Math.abs(nums[i] - nums[j]);

                dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] + 1);

                ans = Math.max(ans, dp[i][diff]);
            }
        }

        return ans;
    }

    public int mySol4(int[] nums) {
        int n = nums.length;
        
        int[] diff = new int[n - 1];

        for (int i = 0; i < n - 1; i++) {
            diff[i] = Math.abs(nums[i] - nums[i + 1]);
        }

        return mySol4(nums, 0, 299, 1, new Integer[n][300]);
    }

    public int mySol4(int[] nums, int index, int diff, int length, Integer[][] memo) {
        if (index >= nums.length) return length;

        int ans = 0;

        for (int i = index + 1; i < nums.length; i++) {
            int currentDiff = Math.abs(nums[i] - nums[index]);

            if (diff >= currentDiff) {
                ans = Math.max(ans, mySol4(nums, i, currentDiff, length + 1, memo));
            }
        }

        ans = Math.max(ans, mySol4(nums, index + 1, diff, length, memo));
        
        return ans;
    }

    public int mySol3(int[] nums) {
        int ans = 0;

        Integer[][] memo = new Integer[nums.length][300];
        
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, mySol3(nums, 0, 299, memo));
        }

        return ans + 1;
    }

    public int mySol3(int[] nums, int index, int diff, Integer[][] memo) {
        if (index >= nums.length || diff == 0) return 0;

        if (memo[index][diff] != null) {
            // return memo[index][diff];
        }
        
        int ans = 0;
        
        for (int i = index + 1; i < nums.length; i++) {
            int currentDiff = Math.abs(nums[i] - nums[index]);

            if (diff >= currentDiff) {
                ans = Math.max(ans, 1 + mySol3(nums, i, currentDiff, memo));
            }
        }

        // ans = Math.max(ans, mySol3(nums, index + 1, 299, memo));

        return memo[index][diff] = ans;
    }

    public int mySol2(int[] nums) {
        return mySol2(nums, nums[0], 1, 300, new Integer[nums.length][300]);
    }

    public int mySol2(int[] nums, int prevValue, int index, int diff, Integer[][] memo) {
        if (index >= nums.length) return 0;

        int ans = 0;

        int currentDiff = Math.abs(prevValue - nums[index]);

        if (diff >= currentDiff) {
            ans = 1 + mySol2(nums, nums[index], index + 1, currentDiff, memo);
        }

        ans += mySol2(nums, prevValue, index + 1, diff, memo);

        return ans;
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int[] lastDiff = new int[n];
        Arrays.fill(lastDiff, Integer.MAX_VALUE);
        // lastDiff[0] = 300;
        
        int[] length = new int[n];

        Arrays.fill(length, 1);

        int ans = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = Math.abs(nums[i] - nums[j]);

                if (lastDiff[j] >= diff) {
                    if (length[i] < length[j] + 1) {
                        length[i] = length[j] + 1;
                        lastDiff[i] = diff;
                        ans = Math.max(ans, length[i]);
                    }
                }
            }

            if (length[i] == 1) {
                length[i] = length[i - 1];
                lastDiff[i] = lastDiff[i - 1];
            }
        }

        System.out.println(Arrays.toString(lastDiff));
        System.out.println(Arrays.toString(length));

        return ans;
    }
}