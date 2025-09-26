class Solution {
    public int triangleNumber(int[] nums) {
        return mySol2(nums);
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

    public int mySol_holding(int[] nums) {
        int n = nums.length;

        if (n < 3) return 0;

        Arrays.sort(nums);

        for (int i = n - 2; i >= 1; i--) {
            int sum = nums[i] + nums[i + 1];

            int index = rightmost(nums, 0, i, sum - 1);
        }

        return 0;
    }

    private int rightmost(int[] nums, int lo, int hi, int target) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (target < nums[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo - 1;
    }
}