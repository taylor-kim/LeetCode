class Solution {
    public int minRemoval(int[] nums, int k) {
        return byHint(nums, k);
    }

    public int byHint(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int ans = 0;

        int left = 0;

        for (int right = 0; right < n; right++) {
            long min = (long)nums[left];
            long max = (long)nums[right];

            if (left <= right && min * k < max) {
                min = (long)nums[++left];
            }
        }

        return left;
    }

    public int others_sw(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;

        int left = 0;
        int max = 0;

        for (int right = 0; right < n; right++) {
            while (left < right && (long)nums[left] * k < (long)nums[right]) {
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return left;
    }

    public int others_bs(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;

        int left = 1;
        int right = n + 1;
        int max = 0;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isPossible(nums, mid, k)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return n - (left - 1);
    }

    private boolean isPossible(int[] nums, int windowSize, int k) {
        for (int i = 0; i + windowSize - 1 < nums.length; i++) {
            if ((long)nums[i] * k >= (long)nums[i + windowSize - 1]) return true;
        }

        return false;
    }

    public int try_20260206_3_topic(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;

        int ans = Integer.MAX_VALUE;

        int left = 0;
        int right = n - 1;

        for (int i = 0; i < n; i++) {
            int to = rightmost(nums, 1l * nums[i] * k, i);

            ans = Math.min(ans, n - to - 1 + i);
        }

        return ans;
    }

    private int rightmost(int[] nums, long target, int lo) {
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }

    public int try_20260206_2_fail(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            if (1l * nums[left] * k >= 1l * nums[right]) return n - right - 1 + left;

            int diffL = nums[left + 1] - nums[left];
            int diffR = nums[right] - nums[right - 1];

            if (diffL >= diffR) {
                left++;
            } else if (diffL < diffR) {
                right--;
            }
        }

        return n - 1;
    }

    public int try_20260206_mle(int[] nums, int k) {
        Arrays.sort(nums);

        return topdown(nums, 0, nums.length - 1, k, new Integer[nums.length][nums.length]);
    }

    private int topdown(int[] nums, int left, int right, int k, Integer[][] memo) {
        if (left == right || 1l * nums[left] * k >= 1l * nums[right]) return nums.length - right - 1 + left;

        if (memo[left][right] != null) return memo[left][right];

        int removeLeft = topdown(nums, left + 1, right, k, memo);
        int removeRight = topdown(nums, left, right - 1, k, memo);

        return memo[left][right] = Math.min(removeLeft, removeRight);
    }
}