class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        return editorial_sliding_window(nums, k);
    }

    public int[] editorial_sliding_window(int[] nums, int k) {
        int windowSumFirst = 0;
        int windowSumSecond = 0;
        int windowSumThird = 0;

        for (int i = 0; i < k; i++) windowSumFirst += nums[i];
        for (int i = k; i < 2 * k; i++) windowSumSecond += nums[i];
        for (int i = 2 * k; i < 3 * k; i++) windowSumThird += nums[i];

        int bestSumSingle = windowSumFirst;
        int bestSumDouble = windowSumFirst + windowSumSecond;
        int bestSumTriple = windowSumFirst + windowSumSecond + windowSumThird;

        int bestSingleIndex = 0;
        int[] bestDoubleIndex = {0, k};
        int[] bestTripleIndex = {0, k, 2 * k};

        for (int i = 2 * k + 1; i <= nums.length - k; i++) {
            int indexFirst = i - (2 * k);
            windowSumFirst += -nums[indexFirst - 1] + nums[indexFirst + k - 1];

            if (windowSumFirst > bestSumSingle) {
                bestSumSingle = windowSumFirst;
                bestSingleIndex = indexFirst;
            }

            int indexSecond = i - k;
            windowSumSecond += -nums[indexSecond - 1] + nums[indexSecond + k - 1];

            if (windowSumSecond + bestSumSingle > bestSumDouble) {
                bestSumDouble = windowSumSecond + bestSumSingle;
                bestDoubleIndex[0] = bestSingleIndex;
                bestDoubleIndex[1] = indexSecond;
            }

            int indexThird = i;
            windowSumThird += -nums[indexThird - 1] + nums[indexThird + k - 1];

            if (windowSumThird + bestSumDouble > bestSumTriple) {
                bestSumTriple = windowSumThird + bestSumDouble;
                bestTripleIndex[0] = bestDoubleIndex[0];
                bestTripleIndex[1] = bestDoubleIndex[1];
                bestTripleIndex[2] = indexThird;
            }
        }

        return bestTripleIndex;
    }

    public int[] try_three_pointers(int[] nums, int k) {
        int n = nums.length;
        int[] pSum = new int[n + 1];
        int[] leftMaxIndex = calcLeftMaxIndex(nums, k);
        int[] rightMaxIndex = calcRightMaxIndex(nums, k);

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
        }

        int totalSum = 0;
        int[] ans = new int[3];

        for (int i = k; i + k - 1 < n - k; i++) {
            int leftStart = leftMaxIndex[i - 1];
            int rightStart = rightMaxIndex[i + k];

            int leftSum = pSum[leftStart + k] - pSum[leftStart];
            int midSum = pSum[i + k] - pSum[i];
            int rightSum = pSum[rightStart + k] - pSum[rightStart];

            int currentSum = leftSum + midSum + rightSum;

            if (totalSum < currentSum) {
                totalSum = currentSum;
                ans[0] = leftStart;
                ans[1] = i;
                ans[2] = rightStart;
            }
        }

        return ans;
    }

    private int[] calcLeftMaxIndex(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int leftSum = 0;
        int maxSoFar = 0;
        int[] leftMaxIndex = new int[n];

        for (int right = 0; right < n; right++) {
            leftSum += nums[right];

            if (right - left + 1 == k) {
                if (maxSoFar < leftSum) {
                    maxSoFar = leftSum;
                    leftMaxIndex[right] = left;
                } else {
                    leftMaxIndex[right] = leftMaxIndex[right - 1];
                }
                leftSum -= nums[left++];
            }
        }

        return leftMaxIndex;
    }

    private int[] calcRightMaxIndex(int[] nums, int k) {
        int n = nums.length;
        int right = n - 1;
        int rightSum = 0;
        int maxSoFar = 0;
        int[] rightMaxIndex = new int[n];

        for (int left = n - 1; left >= 0; left--) {
            rightSum += nums[left];

            if (right - left + 1 == k) {
                if (maxSoFar <= rightSum) {
                    maxSoFar = rightSum;
                    rightMaxIndex[left] = left;
                } else {
                    rightMaxIndex[left] = rightMaxIndex[left + 1];
                }

                rightSum -= nums[right--];
            }
        }

        return rightMaxIndex;
    }

    public int[] editorial_bottomup(int[] nums, int k) {
        int n = nums.length;
        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
        }

        int[][] bestSum = new int[4][n + 1];
        int[][] bestIndex = new int[4][n + 1];

        for (int length = 1; length <= 3; length++) {
            for (int end = length * k; end <= n; end++) {
                int currentSum = pSum[end] - pSum[end - k];
                int prevBestSum = bestSum[length - 1][end - k];

                int include = currentSum + prevBestSum;
                int exclude = bestSum[length][end - 1];

                if (include > exclude) {
                    bestSum[length][end] = include;
                    bestIndex[length][end] = end - k;
                } else {
                    bestSum[length][end] = exclude;
                    bestIndex[length][end] = bestIndex[length][end - 1];
                }
            }
        }

        int[] ans = new int[3];
        int currentEnd = n;

        for (int subIndex = 3; subIndex >= 1; subIndex--) {
            ans[subIndex - 1] = bestIndex[subIndex][currentEnd];
            currentEnd = ans[subIndex - 1];
        }

        return ans;
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