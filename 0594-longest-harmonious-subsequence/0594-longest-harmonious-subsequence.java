class Solution {
    public int findLHS(int[] nums) {
        return mySol3(nums);
    }

    public int mySol3(int[] nums) {
        TreeMap<Integer, Integer> treeMap = new TreeMap();

        for (int num : nums) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
        }

        int ans = 0;
        int prev = treeMap.firstKey();

        while (treeMap.higherKey(prev) != null) {
            int higher = treeMap.higherKey(prev);

            if (higher - prev == 1) {
                ans = Math.max(ans, treeMap.get(higher) + treeMap.get(prev));
            }

            prev = higher;
        }

        return ans;
    }

    public int mySol2_fail(int[] nums) {
        int n = nums.length;
        int[] diff = new int[n - 1];

        for (int i = 0; i < n - 1; i++) {
            diff[i] = Math.abs(nums[i] - nums[i + 1]);
        }

        System.out.println(Arrays.toString(diff));

        return 0;
    }

    public int mySol_fail(int[] nums) {
        int left = 0;

        Deque<int[]> minHeap = new LinkedList();
        Deque<int[]> maxHeap = new LinkedList();

        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];

            while (!minHeap.isEmpty() && minHeap.peekLast()[0] > num) {
                int[] removed = minHeap.pollLast();
            }

            minHeap.addLast(new int[] {num, right});

            while (!maxHeap.isEmpty() && maxHeap.peekLast()[0] < num) {
                int[] removed = maxHeap.pollLast();
            }

            maxHeap.addLast(new int[] {num, right});

            while (maxHeap.peekFirst()[0] - minHeap.peekFirst()[0] > 1) {
                if (maxHeap.peekFirst()[1] < minHeap.peekFirst()[1]) {
                    left = maxHeap.pollFirst()[1] + 1;
                } else {
                    left = minHeap.pollFirst()[1] + 1;
                }
            }

            if (maxHeap.peekFirst()[0] - minHeap.peekFirst()[0] == 1) {
                ans = Math.max(ans, right - left + 1);
            }
        }

        return ans;
    }
}