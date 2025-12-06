class Solution {
    public int countPartitions(int[] nums, int k) {
        return editorial2(nums, k);
    }

    public int editorial2(int[] nums, int k) {
        int n = nums.length;
        int mod = (int)1e9 + 7;
        long[] dp = new long[n + 1];
        long[] pSum = new long[n + 1];
        dp[0] = 1;
        pSum[0] = 1;

        Deque<Integer> minHeap = new LinkedList();
        Deque<Integer> maxHeap = new LinkedList();

        for (int i = 0, j = 0; i < n; i++) {
            while (!minHeap.isEmpty() && nums[minHeap.peekLast()] > nums[i]) {
                minHeap.pollLast();
            }

            while (!maxHeap.isEmpty() && nums[maxHeap.peekLast()] < nums[i]) {
                maxHeap.pollLast();
            }

            minHeap.add(i);
            maxHeap.add(i);

            while (j <= i && nums[maxHeap.peekFirst()] - nums[minHeap.peekFirst()] > k) {
                if (maxHeap.peekFirst() == j) {
                    maxHeap.pollFirst();
                }

                if (minHeap.peekFirst() == j) {
                    minHeap.pollFirst();
                }
                j++;
            }

            dp[i + 1] = (pSum[i] - (j > 0 ? pSum[j - 1] : 0) + mod) % mod;
            pSum[i + 1] = (pSum[i] + dp[i + 1]) % mod;
        }

        return (int)dp[n];
    }

    public int editorial(int[] nums, int k) {
        int n = nums.length;
        int mod = (int)1e9 + 7;
        long[] dp = new long[n + 1];
        long[] pSum = new long[n + 1];
        dp[0] = 1;
        pSum[0] = 1;

        TreeMap<Integer, Integer> treeMap = new TreeMap();

        for (int i = 0, j = 0; i < n; i++) {
            treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);

            while (j <= i && treeMap.lastKey() - treeMap.firstKey() > k) {
                treeMap.put(nums[j], treeMap.get(nums[j]) - 1);
                if (treeMap.get(nums[j]) == 0) {
                    treeMap.remove(nums[j]);
                }
                j++;
            }

            dp[i + 1] = (pSum[i] - (j > 0 ? pSum[j - 1] : 0) + mod) % mod;
            pSum[i + 1] = (pSum[i] + dp[i + 1]) % mod;
        }

        return (int)dp[n];
    }

    public int mySol2_fail(int[] nums, int k) {
        Deque<Integer> minHeap = new LinkedList();
        Deque<Integer> maxHeap = new LinkedList();
        int mod = (int)1e9 + 7;
        long[] dp = new long[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            while (!minHeap.isEmpty() && nums[minHeap.peekLast()] > nums[i]) {
                minHeap.pollLast();
            }

            while (!maxHeap.isEmpty() && nums[maxHeap.peekLast()] < nums[i]) {
                maxHeap.pollLast();
            }

            minHeap.add(i);
            maxHeap.add(i);

            int minIndex = minHeap.peekFirst();
            int maxIndex = maxHeap.peekFirst();

            int min = nums[minIndex];
            int max = nums[maxIndex];

            System.out.println("i:%d, min:%d, max:%d, minIndex:%d, maxIndex:%d".formatted(i, min, max, minIndex, maxIndex));

            if (max - min <= k) {
                int left = Math.min(minHeap.peekFirst(), maxHeap.peekFirst());
                dp[i] = (dp[i] * dp[left]) % mod;
            } 
        }

        return (int)dp[nums.length - 1];
    }

    public int mySol_fail(int[] nums, int k) {
        return topdown(nums, k, 0, new Integer[nums.length]);
    }

    public int topdown(int[] nums, int k, int index, Integer[] memo) {
        if (index >= nums.length) return 1;

        if (memo[index] != null) return memo[index];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int ans = 0;
        int mod = (int)1e9 + 7;

        for (int i = index; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);

            if (max - min <= k) {
                ans = (ans + topdown(nums, k, i + 1, memo)) % mod;
            } else {
                break;
            }
        }

        return memo[index] = ans;
    }
}