class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        return editorial_memo(nums, k);
    }

    public int[] editorial_memo(int[] nums, int k) {
        int n = nums.length;
        int[] kSum = new int[n - k + 1];

        int left = 0;
        int right = 0;
        int sum = 0;

        for (right = 0; right < n; right++) {
            sum += nums[right];

            if (right - left + 1 == k) {
                kSum[left] = sum;
                sum -= nums[left++];
            }
        }

        Integer[][] memo = new Integer[kSum.length][4];

        int[] ans = new int[3];

        editorial_topdown(kSum, 0, 3, k, memo);
        dfs(kSum, 0, 3, k, memo, ans);

        return ans;
    }

    private int editorial_topdown(int[] kSum, int index, int remain, int k, Integer[][] memo) {
        if (remain == 0) return 0;
        if (index >= kSum.length) return remain > 0 ? Integer.MIN_VALUE : 0;

        if (memo[index][remain] != null) return memo[index][remain];

        int include = kSum[index] + editorial_topdown(kSum, index + k, remain - 1, k, memo);
        int exclude = editorial_topdown(kSum, index + 1, remain, k, memo);

        return memo[index][remain] = Math.max(include, exclude);
    }

    private void dfs(int[] kSum, int index, int remain, int k, Integer[][] memo, int[] ans) {
        if (remain == 0 || index >= kSum.length) return;

        int include = kSum[index] + editorial_topdown(kSum, index + k, remain - 1, k, memo);
        int exclude = editorial_topdown(kSum, index + 1, remain, k, memo);

        if (include >= exclude) {
            // System.out.println(String.format("ans[%d] = %d, (remain:%d)", 3 - remain, index, remain));
            ans[3 - remain] = index;
            dfs(kSum, index + k, remain - 1, k, memo, ans);
        } else {
            dfs(kSum, index + 1, remain, k, memo, ans);
        }
    }

    public int[] mySol2_fail(int[] nums, int k) {
        int n = nums.length;
        int[] kSum = new int[n - k + 1];

        int left = 0;
        int right = 0;
        int sum = 0;

        for (right = 0; right < n; right++) {
            sum += nums[right];

            if (right - left + 1 == k) {
                kSum[left] = sum;
                sum -= nums[left++];
            }
        }

        // System.out.println(Arrays.toString(kSum));

        left = 0;
        right = n - k;

        int totalSum = 0;

        int[] ans = {-1, -1, -1};

        // while (right - 1 - left + k + 1 >= k) {
        // while (left + k <= right - k) {
        //     int mid = getMaxSumIndex(kSum, left + k, right - k);

        //     System.out.println(String.format("left:%d, mid:%d, right:%d", left, mid, right));

        //     int currentSum = kSum[left] + kSum[mid] + kSum[right];

        //     if (totalSum < currentSum || (totalSum == currentSum && right < ans[2])) {
        //         totalSum = currentSum;
        //         ans[0] = left;
        //         ans[1] = mid;
        //         ans[2] = right;
        //     }

        //     if (kSum[left + 1] > kSum[right - 1]) {
        //         left++;
        //     } else {
        //         right--;
        //     }
        // }

        topdown(kSum, left, right, k, 0, ans);

        return ans;
    }

    private void topdown(int[] kSum, int left, int right, int k, int maxSum, int[] ans) {
        if (left + k > right - k) return;

        int mid = getMaxSumIndex(kSum, left + k, right - k);

        int currentSum = kSum[left] + kSum[mid] + kSum[right];

        if (maxSum < currentSum || (maxSum == currentSum && isLower(ans, left, mid, right))) {
            ans[0] = left;
            ans[1] = mid;
            ans[2] = right;

            maxSum = currentSum;
        }

        topdown(kSum, left + 1, right, k, maxSum, ans);
        topdown(kSum, left, right - 1, k, maxSum, ans);
    }

    private boolean isLower(int[] ans, int left, int mid, int right) {
        return left < ans[0] || mid < ans[1] || right < ans[2];
    }

    private int getMaxSumIndex(int[] kSum, int left, int right) {
        int index = left;
        int max = kSum[left];

        for (int i = left + 1; i <= right; i++) {
            if (max < kSum[i]) {
                max = kSum[i];
                index = i;
            }
        }

        return index;
    }

    public int[] mySol_hold(int[] nums, int k) {
        int[] ans = {-1, -1, -1};
        

        int[][] windows = new int[3][3];

        for (int i = 0; i < windows.length; i++) {
            int[] window = windows[i];

            if (i > 0) {
                window[0] = windows[i - 1][1] + 1;
            }
            window[1] = window[0] + k - 1;
        }

        for (int i = 0; i < 3; i++) {
            windows[i] = getWindow(nums, i * k, k);
            ans[i] = windows[i][0];
        }

        // for (int right = windows[2][1])
        // for (int windowNumber = 2; windowNumber >= 0; windowNumber--) {
        //     updateWindow(windows, ans, windowNumber);
        // }

        return ans;
    }

    private int[] getWindow(int[] nums, int start, int k) {
        int[] window = {start, start + k - 1, 0};

        for (int i = start; i < start + k; i++) {
            window[2] += nums[i];
        }

        return window;
    }
}