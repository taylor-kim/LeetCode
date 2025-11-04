class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        return mySol(nums, k, x);
    }

    public int[] mySol(int[] nums, int k, int x) {
        int n = nums.length - k + 1;
        int[] ans = new int[n];

        int[] freq = new int[51];

        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            freq[nums[right]]++;

            if (right - left + 1 == k) {
                Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
                    return a[1] != b[1] ? b[1] - a[1] : b[0] - a[0];
                });
                for (int top = freq.length - 1; top > 0; top--) {
                    if (freq[top] != 0) {
                        pq.add(new int[] {top, freq[top]});
                    }
                }

                for (int i = 0; i < x && !pq.isEmpty(); i++) {
                    int[] data = pq.poll();
                    ans[left] += data[0] * data[1];
                }
                freq[nums[left++]]--;
            }
        }

        return ans;
    }
}