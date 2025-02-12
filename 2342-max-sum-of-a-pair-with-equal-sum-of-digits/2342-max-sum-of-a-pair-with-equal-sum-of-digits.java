class Solution {
    public int maximumSum(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        Map<Integer, Queue<Integer>> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            int sumOfDigit = 0;

            while (num > 0) {
                sumOfDigit += num % 10;
                num /= 10;
            }

            map.computeIfAbsent(sumOfDigit, k -> new PriorityQueue<>((a, b) -> b - a)).add(nums[i]);
        }

        int ans = -1;

        for (Queue<Integer> queue : map.values()) {
            if (queue.size() >= 2) {
                int sum = queue.poll() + queue.poll();

                ans = Math.max(ans, sum);
            }
        }

        return ans;
    }
}