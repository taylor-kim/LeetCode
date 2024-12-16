class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        return mySol(nums, k, multiplier);
    }

    public int[] mySol(int[] nums, int k, int multiplier) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        for (int i = 0; i < nums.length; i++) {
            pq.add(new int[] {nums[i], i});
        }

        while (k-- > 0) {
            int[] data = pq.poll();
            data[0] *= multiplier;

            nums[data[1]] = data[0];

            pq.add(data);
        }

        return nums;
    }
}