class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        return try_after_editorial(nums, queries);
    }

    public int[] try_after_editorial(int[] nums, long[] queries) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);

        long[] counter = new long[max + 1];

        for (int num : nums) {
            counter[num]++;
        }

        for (int gcd = 1; gcd <= max; gcd++) {
            for (int multi = gcd * 2; multi <= max; multi += gcd) {
                counter[gcd] += counter[multi];
            }
        }

        for (int gcd = 1; gcd <= max; gcd++) {
            counter[gcd] = (counter[gcd] * (counter[gcd] - 1)) / 2;
        }

        for (int gcd = max; gcd >= 1; gcd--) {
            for (int multi = gcd * 2; multi <= max; multi += gcd) {
                counter[gcd] -= counter[multi];
            }
        }

        for (int i = 1; i < counter.length; i++) {
            counter[i] += counter[i - 1];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long q = queries[i] + 1;
            int lo = 1;
            int hi = max + 1;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                
                if (counter[mid] < q) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            ans[i] = lo;
        }

        return ans;
    }
}