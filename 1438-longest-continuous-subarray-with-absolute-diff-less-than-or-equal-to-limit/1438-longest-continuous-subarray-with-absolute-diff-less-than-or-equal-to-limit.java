class Solution {
    public int longestSubarray(int[] nums, int limit) {
        return try_again_20250615(nums, limit);
    }

    public int try_again_20250615(int[] nums, int limit) {
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            return b[0] != a[0] ? b[0] - a[0] : a[1] - b[1];
        });

        int left = 0;

        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            int[] data = new int[] {nums[right], right};

            minHeap.add(data);
            maxHeap.add(data);

            while (maxHeap.peek()[0] - minHeap.peek()[0] > limit) {
                left = Math.min(maxHeap.peek()[1], minHeap.peek()[1]) + 1;

                while (maxHeap.peek()[1] < left) maxHeap.poll();
                while (minHeap.peek()[1] < left) minHeap.poll();
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }

    public int try_by_hint_fail(int[] nums, int limit) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        // max = nums[0];
        // min = nums[0];

        int ans = 0;

        int left = 0;

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        for (int right = 0; right < nums.length; right++) {
            max = Math.max(nums[right], max);
            min = Math.min(nums[right], min);

            int diff = max - min;

            while (left <= right && diff > limit) {
                max = Math.max(nums[left], max);
                min = Math.min(nums[left++], min);

                diff = max - min;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}