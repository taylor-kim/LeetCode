class Solution {
    public int minOperations(int[] nums, int k) {
        return others_int(nums, k);
    }

    public int others_int(int[] nums, int k) {
        Queue<Integer> pq = new PriorityQueue();

        for (int num : nums) pq.add(num);

        int ans = 0;

        while (pq.size() >= 2 && pq.peek() < k) {
            int x = pq.poll();
            int y = pq.poll();

            if (x >= (Integer.MAX_VALUE  - y) / 2) {
                pq.add(k);
            } else {
                pq.add(2 * x + y);
            }

            ans++;
        }

        return ans;
    }

    public int mySol(int[] nums, int k) {
        Queue<Long> pq = new PriorityQueue();

        for (int num : nums) pq.add((long)num);

        int ans = 0;

        long kl = (long)k;

        while (pq.size() >= 2 && pq.peek() < kl) {
            long x = pq.poll();
            long y = pq.poll();

            // pq.add(Math.min(x, y) * 2 + Math.max(x, y));
            pq.add(2 * x + y);

            ans++;
        }

        return ans;
    }
}