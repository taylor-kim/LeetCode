class Solution {
    public int triangleNumber(int[] nums) {
        return try_bs(nums);
    }

    public int try_bs(int[] nums) {
        int n = nums.length;

        if (n < 3) return 0;

        Arrays.sort(nums);

        int ans = 0;

        for (int i = 0; i < n - 2; i++) {
            if (nums[i] == 0) continue;
            int k = i + 2;
            for (int j = i + 1; j < n - 1; j++) {
                if (nums[j] == 0) continue;
                int sum = nums[i] + nums[j];

                // k = rightmost(nums, k, n, sum - 1);

                // ans += k - j - 1;

                int index = rightmost(nums, j + 1, n, sum - 1);

                ans += index - j - 1;
            }
        }

        return ans;
    }

    public int mySol2(int[] nums) {
        int n = nums.length;

        if (n < 3) return 0;

        Arrays.sort(nums);

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                for (int k = j + 1; k < n; k++) {
                    if (sum > nums[k]) {
                        ans++;
                    } else {
                        break;
                    }
                }
            }
        }

        return ans;
    }

    public int mySol_holding_fail(int[] nums) {
        int n = nums.length;

        if (n < 3) return 0;

        Arrays.sort(nums);

        int ans = 0;

        for (int i = n - 1; i >= 2; i--) {
            for (int j = i - 1; j >= 1; j--) {
                int sum = nums[i] + nums[j];

                int index = rightmost(nums, 0, j, sum - 1);

                ans += index + 1;
            }
        }

        return ans;
    }

    private int rightmost(int[] nums, int lo, int hi, int target) {
        // String s = String.format("lo:%d, hi:%d", lo, hi);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        // System.out.println(String.format("%s, target:%d, index:%d", s, target, lo));

        return lo;
    }
}