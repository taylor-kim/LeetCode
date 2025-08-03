class Solution {
    public int minRemoval(int[] nums, int k) {
        return gpt(nums, k);
    }

    public int gpt(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            while ((long)nums[right] > (long)nums[left] * k) {
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return n - maxLen;
    }


    public int others_bs(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        int lo = 0;
        int hi = n;
        int max = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (isPossible(nums, mid, k)) {
                max = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return n - max;
    }

    public boolean isPossible(int[] nums, int windowSize, int k) {
        if (windowSize == 0) return true;

        for (int i = 0; i + windowSize - 1 < nums.length; i++) {
            if (1l * nums[i] * k >= nums[i + windowSize - 1]) return true;
        }

        return false;
    }

    public int lee(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int ans = 0;

        int left = 0;

        for (int right = 0; right < n; right++) {
            long min = (long)nums[left];
            long max = (long)nums[right];

            while (left <= right && min * k < max) {
                min = (long)nums[++left];
            }
        }

        return left;
    }

    public int sol4_fail(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        // k = 3
        // 1,2,5
        // 2,4,10
        // 1,3,20

        int left = 0;
        int right = n - 1;

        int ans = 0;

        System.out.println(Arrays.toString(nums));

        while (left < right) {
            long min = (long)nums[left];
            long max = (long)nums[right];

            if (min * k >= max) return ans;

            int i1 = leftmost(nums, left, right, min * k + 1);
            int remove1 = right - i1 + 1;

            long target = max / k + (max % k == 0 ? 0 : 1);
            int i2 = leftmost(nums, left, right, target);
            int remove2 = i2 - left;

            if (remove1 < remove2) {
                right -= remove1;
                ans += remove1;
            } else {
                left += remove2;
                ans += remove2;
            }
        }

        return ans;
    }

    private int leftmost(int[] nums, int lo, int hi, long target) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (target <= nums[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public int sol3_mle(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });
        queue.add(new int[] {0, n - 1, 0});

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int left = queue.peek()[0];
                int right = queue.peek()[1];
                int remove = queue.poll()[2];

                if (left >= right) continue;

                long min = (long)nums[left];
                long max = (long)nums[right];

                if (min * k >= max) return remove;

                int lc = 1;

                while (left + lc < right && nums[left] == nums[left + lc]) {
                    lc++;
                }

                queue.add(new int[] {left + lc, right, remove + lc});

                int rc = 1;

                while (left < right - rc && nums[right - rc] == nums[right]) {
                    rc++;
                }

                queue.add(new int[] {left, right - rc, remove + rc});
            }
        }

        return 0;
    }

    public int sol2_mle(int[] nums, int k) {
        Arrays.sort(nums);

        return topdown(nums, 0, nums.length - 1, k, new Integer[nums.length][nums.length]);
    }

    private int topdown(int[] nums, int left, int right, int k, Integer[][] memo) {
        if (left >= right) return 0;

        if (memo[left][right] != null) {
            return memo[left][right];
        }

        long min = (long)nums[left];
        long max = (long)nums[right];

        if (min * k >= max) return 0;

        int lc = 1;

        while (left + lc < right && nums[left] == nums[left + lc]) {
            lc++;
        }

        int case1 = lc + topdown(nums, left + lc, right, k, memo);

        int rc = 1;

        while (left < right - rc && nums[right - rc] == nums[right]) {
            rc++;
        }

        int case2 = rc + topdown(nums, left, right - rc, k, memo);

        return memo[left][right] = Math.min(case1, case2);
    }
    
    public int sol1_fail(int[] nums, int k) {
        Arrays.sort(nums);

        // k = 2
        // 1, 23, 34
        
        // dp[0] = 0, dp[1] = 1, dp[2]

        int ans = 0;

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int min = nums[left];
            int max = nums[right];
            
            if (min * k >= max) return ans;

            int lc = 1;

            while (left + lc < right && nums[left] == nums[left + lc]) {
                lc++;
            }

            int rc = 1;

            while (left < right - rc && nums[right - rc] == nums[right]) {
                rc++;
            }

            System.out.println(String.format("left:%d, right:%d", left, right));

            if (lc < rc) {
                ans += lc;
                left += lc;
            } else {
                ans += rc;
                right -= rc;
            }
        }

        return ans;
    }
}