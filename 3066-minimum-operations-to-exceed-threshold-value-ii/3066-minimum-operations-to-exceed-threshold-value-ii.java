class Solution {
    public int minOperations(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        Queue<Long> pq = new PriorityQueue();

        for (int num : nums) pq.add((long)num);

        int ans = 0;

        long kl = (long)k;

        while (pq.size() >= 2 && pq.peek() < kl) {
            long x = pq.poll();
            long y = pq.poll();

            pq.add(Math.min(x, y) * 2 + Math.max(x, y));

            ans++;
        }

        return ans;
    }
}