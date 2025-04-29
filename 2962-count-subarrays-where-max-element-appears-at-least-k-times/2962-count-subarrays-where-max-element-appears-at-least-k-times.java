class Solution {
    public long countSubarrays(int[] nums, int k) {
        return mySol(nums, k);
    }

    public long mySol(int[] nums, int k) {
        int n = nums.length;
        int max = 0;

        for (int num : nums) max = Math.max(max, num);

        long ans = 0;
        List<Integer> indices = new ArrayList();

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            if (max != num) continue;

            indices.add(i);
        }

        int prev = -1;

        for (int i = 0; i < indices.size() - k + 1; i++) {
            int left = indices.get(i);
            int right = indices.get(i + k - 1);

            long leftCount = left - prev;
            long rightCount = n - right;

            ans += (leftCount * rightCount);

            prev = left;

            // System.out.println(String.format("i:%d, left:%d, right:%d, leftCount:%d, rightCount:%d, ans:%d"
            //                                 , i, left, right, leftCount, rightCount, ans));
        }

        return ans;
    }
}