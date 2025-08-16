class Solution {
    public int maximumLength(int[] nums) {
        return tryAgain_lis(nums);
    }

    public int tryAgain_lis(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[2][n];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            // dp[0][i] = 1;
            // dp[1][i] = 1;
            // for (int j = 0; j < i; j++) {
            //     int mod = (nums[j] + nums[i]) % 2;
            //     dp[mod][i] = Math.max(dp[mod][i], dp[mod][j] + 1);
            //     ans = Math.max(ans, dp[mod][i]);
            // }
            for (int mod = 0; mod < 2; mod++) {
                dp[mod][num % 2] = dp[num % 2][mod] + 1;
                ans = Math.max(ans, dp[mod][num % 2]);
            }
        }

        return ans;
    }

    public int origin_mySol(int[] nums) {
        int n = nums.length;
        int[] oddDp = new int[n];
        int[] evenDp = new int[n];
        oddDp[0] = 1;
        evenDp[0] = 1;

        int ans = 1;

        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 0; j < i; j++) {
                if ((nums[j] + nums[i]) % 2 == 0) {
                    evenDp[i] = Math.max(evenDp[i], evenDp[j] + 1);
                    // oddDp[i] = 1;
                } else {
                    oddDp[i] = Math.max(oddDp[i], oddDp[j] + 1);
                    // evenDp[i] = 1;
                }
            }

            ans = Math.max(ans, Math.max(evenDp[i], oddDp[i]));
        }

        // System.out.println(Arrays.toString(evenDp));
        // System.out.println(Arrays.toString(oddDp));

        // return Math.max(oddDp[n - 1], evenDp[n - 1]);
        return ans;
    }

    public int try_20250816_2(int[] nums) {
        int zeros = 0;
        int ones = 0;
        int pingPong = 0;
        int prevMod = -1;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int mod = num % 2;

            if (mod == 0) {
                zeros++;
            } else {
                ones++;
            }

            if (prevMod != mod) {
                pingPong++;
                prevMod = mod;
            }
        }

        return Math.max(pingPong, Math.max(zeros, ones));
    }

    public int try_20250816_fail(int[] nums) {
        return Math.max(
            topdown(nums, 0, 0, 0),
            // topdown(nums, 1, 0, 0)
            0
        );
    }

    public int topdown(int[] nums, int mod, int prev, int index) {
        if (index >= nums.length) return 0;

        int ans = 0;

        if (prev == index) {
            int include = 1 + topdown(nums, mod, index, index + 1);
            int exclude = topdown(nums, mod, prev, index + 1);

            ans = Math.max(include, exclude);
        } else if ((nums[prev] + nums[index]) % 2 == mod) {
            ans = 1 + topdown(nums, mod, index, index + 1);
        } else {
            ans = Math.max(ans, topdown(nums, mod, prev, index + 1));
            ans = Math.max(ans, topdown(nums, mod, index, index + 1));
        }

        return ans;
    }
}