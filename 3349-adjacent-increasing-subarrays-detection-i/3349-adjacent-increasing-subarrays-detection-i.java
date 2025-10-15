class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        return mySol2(nums, k);
    }

    public boolean mySol2(List<Integer> nums, int k) {
        int n = nums.size();
        int a = 0;
        int b = k;

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

    public boolean mySol(List<Integer> nums, int k) {
        int prev = Integer.MIN_VALUE;
        int length = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (isInc(i, nums, k) && isInc(i + k, nums, k)) return true;
        }

        return false;
    }

    private boolean isInc(int start, List<Integer> nums, int k) {
        int prev = Integer.MIN_VALUE;
        int length = 0;

        for (int i = start; i < start + k && i < nums.size(); i++) {
            int num = nums.get(i);

            if (prev < num) {
                length++;
            } else {
                length = 1;
            }

            if (length == k) {
                // System.out.println(String.format("start:%d, k:%d, i:%d", start, k, i));
                return true;
            }

            prev = num;
        }

        return false;
    }
}