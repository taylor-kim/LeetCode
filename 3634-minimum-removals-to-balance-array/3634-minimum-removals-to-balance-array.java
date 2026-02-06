class Solution {
    public int minRemoval(int[] nums, int k) {
        return try_20260206_3_topic(nums, k);
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