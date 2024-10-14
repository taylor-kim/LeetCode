class Solution {
    public long maxKelements(int[] nums, int k) {
        return mySol2(nums, k);
    }

    public long mySol2(int[] nums, int k) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int num : nums) pq.add(num);

        long ans = 0;

        while (k-- > 0) {
            ans += pq.peek();
            pq.add((int)Math.ceil(pq.poll() / 3.0));
        }

        return ans;
    }

    public long mySol_fail(int[] nums, int k) {
        Arrays.sort(nums);

        long ans = 0;

        int index = nums.length - 1;

        while (index >= 0 && k-- > 0) {
            ans += nums[index];
            nums[index] = (int)Math.ceil(nums[index] / 3.0);

            int next = index == 0 ? nums.length - 1 : index - 1;

            if (nums[index] < nums[next]) {
                index = next;
            }
        }

        return ans;
    }
}