class Solution {
    public int minOperations(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        Queue<Integer> pq = new PriorityQueue();

        for (int num : nums) pq.add(num);

        int ans = 0;

        while (pq.size() >= 2 && pq.peek() < k) {
            int x = pq.poll();
            int y = pq.poll();

            pq.add(Math.min(x, y) * 2 + Math.max(x, y));

            ans++;
        }

        return ans;
    }
}