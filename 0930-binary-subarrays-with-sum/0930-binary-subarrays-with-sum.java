class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return mySol_psum(nums, goal);
    }

    public int try_better_than_square_n(int[] nums, int goal) {
        int n = nums.length;
        int ans = 0;



        return ans;
    }

    public int mySol_sliding_window_fail(int[] nums, int goal) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        int sum = 0;
        int ans = 0;

        while (left < n) {
            if (right < n) {
                sum += nums[right];
            } else {
                sum -= nums[left++];
            }

            while (sum > goal) {
                sum -= nums[left++];
            }

            if (sum == goal) ans++;

            right++;
        }

        return ans;
    }

    public int mySol_psum(int[] nums, int goal) {
        int n = nums.length;
        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + nums[i];
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                int sum = pSum[i] - pSum[j];
                if (sum < goal) break;
                if (sum == goal) ans++;
            }
        }

        return ans;
    }

    public int mySol(int[] nums, int goal) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int base = 0;

            for (int j = i; j < nums.length; j++) {
                base += nums[j];

                if (base == goal) ans++;
            }
        }

        return ans;
    }
}