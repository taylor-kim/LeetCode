class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return official_slidingwindow_onepass(nums, goal);
    }

    public int official_slidingwindow_onepass(int[] nums, int goal) {
        int n = nums.length;
        int left = 0;
        int totalCount = 0;
        int sum = 0;
        int prefixZeros = 0;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            while (left < right && (nums[left] == 0 || sum > goal)) {
                if (nums[left] == 1) {
                    prefixZeros = 0;
                } else {
                    prefixZeros++;
                }

                sum -= nums[left++];
            }

            if (sum == goal) {
                totalCount += 1 + prefixZeros;
            }
        }

        return totalCount;
    }

    public int official_slidingwindow(int[] nums, int goal) {
        return slidingwindowAtMost(nums, goal) - slidingwindowAtMost(nums, goal - 1);
    }

    private int slidingwindowAtMost(int[] nums, int goal) {
        int n = nums.length;
        int left = 0;
        int totalCount = 0;
        int sum = 0;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            while (sum > goal && left <= right) {
                sum -= nums[left++];
            }

            totalCount += right - left + 1;
        }

        return totalCount;
    }

    public int official_prefix_sum(int[] nums, int goal) {
        Map<Integer, Integer> freq = new HashMap();
        int ans = 0;
        int currentSum = 0;

        for (int num : nums) {
            currentSum += num;

            if (currentSum == goal) ans++;

            if (freq.containsKey(currentSum - goal)) {
                ans += freq.get(currentSum - goal);
            }

            freq.put(currentSum, freq.getOrDefault(currentSum, 0) + 1);
        }

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

            while (left < n && sum == goal) {
                ans++;
                sum -= nums[left++];
            }

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