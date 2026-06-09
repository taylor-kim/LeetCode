class Solution {
    public long maxTotalValue(int[] nums, int k) {
        return mySol2(nums, k);
    }

    public long mySol2(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return (long)(max - min) * k;
    }

    public long mySol_hold(int[] nums, int k) {
        long ans = 0;

        Deque<Integer> minHeap = new LinkedList();
        Deque<Integer> maxHeap = new LinkedList();
        Queue<Integer> pq = new PriorityQueue();

        int left = 0;

        for (int right = 0 ; right < nums.length; right++) {
            while (!minHeap.isEmpty() && minHeap.peekLast() > nums[right]) {
                minHeap.pollLast();
            }

            while (!maxHeap.isEmpty() && maxHeap.peekLast() < nums[right]) {
                maxHeap.pollLast();
            }

            minHeap.addLast(nums[right]);
            maxHeap.addLast(nums[right]);

            System.out.println(minHeap);
            System.out.println(maxHeap);

            pq.add(maxHeap.peekFirst() - minHeap.peekFirst());

            // System.out.println(pq + "\n");

            while (pq.size() > k) {
                pq.poll();
            }
        }

        for (int num : pq) {
            ans += num;
        }

        return ans;
    }
}