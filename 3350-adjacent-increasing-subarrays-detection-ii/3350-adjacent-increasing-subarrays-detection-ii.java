class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        return mySol2_with_bs(nums);
    }

    public int mySol2_with_bs(List<Integer> nums) {
        int n = nums.size();
        int max = n / 2 + 1;
        int lo = 1;
        int hi = max;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (mySol2(nums, mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }

    public boolean mySol2(List<Integer> nums, int k) {
        int n = nums.size();
        int a = 0;
        int b = k;

        int ans = 0;

        while (b < n) {
            int count = 1;
            int prevAv = nums.get(a);
            int prevBv = nums.get(b);
            while (a + count < n && b + count < n) {
                int av = nums.get(a + count);

                if (prevAv >= av) break;

                prevAv = av;

                int bv = nums.get(b + count);

                if (prevBv >= bv) break;

                prevBv = bv;

                count++;

                if (count == k) return true;
            }

            if (count >= k) return true;

            a += count;
            b += count;
        }

        return false;
    }

    public int mySol(List<Integer> nums) {
        int ans = 0;
        int count = 1;
        int prevCount = 0;

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) < nums.get(i)) {
                count++;
            } else {
                prevCount = count;
                count = 1;
            }

            ans = Math.max(ans, Math.min(prevCount, count));
            ans = Math.max(ans, count / 2);
        }

        return ans;
    }
}