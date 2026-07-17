class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] cntX = new int[mx + 1];
        for (int x : nums) {
            cntX[x]++;
        }

        long[] cntGcd = new long[mx + 1];
        for (int i = mx; i > 0; i--) {
            int c = 0;
            for (int j = i; j <= mx; j += i) {
                c += cntX[j];
                cntGcd[i] -= cntGcd[j]; // gcd 是 2i,3i,4i,... 的数对不能统计进来
            }
            cntGcd[i] += (long) c * (c - 1) / 2; // c 个数选 2 个，组成 c*(c-1)/2 个数对
        }

        for (int i = 2; i <= mx; i++) {
            cntGcd[i] += cntGcd[i - 1]; // 原地求前缀和
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = upperBound(cntGcd, queries[i]);
        }
        return ans;
    }

    private int upperBound(long[] nums, long target) {
        int left = -1, right = nums.length; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] <= target
            // nums[right] > target
            int mid = (left + right) >>> 1;
            if (nums[mid] > target) {
                right = mid; // 二分范围缩小到 (left, mid)
            } else {
                left = mid; // 二分范围缩小到 (mid, right)
            }
        }
        return right;
    }
}
