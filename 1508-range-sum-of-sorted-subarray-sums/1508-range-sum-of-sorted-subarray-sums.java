class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        return official_binarySearch_and_slidingWindow(nums, n, left, right);
    }

    private static final int MOD = 1000000007;

    public int official_binarySearch_and_slidingWindow(int[] nums, int n, int left, int right) {
        long result =
            (sumOfFirstK(nums, n, right) - sumOfFirstK(nums, n, left - 1)) % MOD;
        // Ensure non-negative result
        return (int) ((result + MOD) % MOD);
    }

    // Helper function to count subarrays with sum <= target and their total sum.
    private Pair<Integer, Long> countAndSum(
        int[] nums,
        int n,
        int target
    ) {
        int count = 0;
        long currentSum = 0, totalSum = 0, windowSum = 0;
        for (int j = 0, i = 0; j < n; ++j) {
            currentSum += nums[j];
            windowSum += nums[j] * (j - i + 1);
            while (currentSum > target) {
                windowSum -= currentSum;
                currentSum -= nums[i++];
            }
            count += j - i + 1;
            totalSum += windowSum;
        }
        return new Pair<>(count, totalSum);
    }

    // Helper function to find the sum of the first k smallest subarray sums.
    private long sumOfFirstK(int[] nums, int n, int k) {
        int minSum = Arrays.stream(nums).min().getAsInt();
        int maxSum = Arrays.stream(nums).sum();
        int left = minSum, right = maxSum;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (countAndSum(nums, n, mid).getKey() >= k) right = mid - 1;
            else left = mid + 1;
        }
        Pair<Integer, Long> result = countAndSum(nums, n, left);
        // There can be more subarrays with the same sum of left.
        return result.getValue() - left * (result.getKey() - k);
    }

    public int official_pq(int[] nums, int n, int left, int right) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 0; i < n; i++) {
            pq.add(new int[] {nums[i], i});
        }

        int ans = 0;
        int mod = (int)1e9 + 7;

        for (int i = 1; i <= right; i++) {
            int[] d = pq.poll();

            if (i >= left) {
                ans = (ans + d[0]) % mod;
            }
            if (d[1] + 1 < n) {
                d[0] += nums[++d[1]];
                pq.add(d);
            }
        }

        return ans;
    }

    public int mySol_bruteForce(int[] nums, int n, int left, int right) {
        List<Integer> list = subarray(nums);

        Collections.sort(list);

        long sum = 0;

        for (int i = left - 1; i < right; i++) {
            sum += list.get(i);
        }

        int mod = (int)1e9 + 7;

        return (int)(sum % mod);
    }

    public List<Integer> subarray(int[] nums) {
        List<Integer> list = new ArrayList();

        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            list.add(sum);

            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                list.add(sum);
            }
        }

        return list;
    }
}