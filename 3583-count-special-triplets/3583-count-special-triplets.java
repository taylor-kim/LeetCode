class Solution {
    public int specialTriplets(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int n = nums.length;

        Map<Integer, List<Integer>> freq = new HashMap();
        
        for (int i = 0; i < n; i++) {
            freq.computeIfAbsent(nums[i], k -> new ArrayList()).add(i);
        }

        int ans = 0;
        int mod = (int)1e9 + 7;

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            List<Integer> ikList = freq.get(num);

            int lastK = ikList.getLast();

            if (lastK - i <= 1) continue;

            int indexOfK = leftmost(ikList, i + 1);

            if (indexOfK >= nums.length) continue;

            while (indexOfK < ikList.size()) {
                int k = ikList.get(indexOfK);

                // System.out.println("ikList:%s, i:%d, k:%d, num:%d".formatted(ikList, i, k, num));

                if (k - i <= 1) {
                    indexOfK++;
                    continue;
                }

                int half = num / 2;

                List<Integer> jList = freq.get(half);

                // System.out.println("wtf!!! jList:%s".formatted(jList));

                if (jList == null) continue;

                int startJ = leftmost(jList, i + 1);
                int endJ = leftmost(jList, k);

                // System.out.println("list:%s, startJ:%d, endJ:%d". formatted(jList, startJ, endJ));

                ans = (ans + (endJ - startJ)) % mod;

                indexOfK++;
            }
        }

        return ans;
    }

    private int leftmost(List<Integer> list, int target) {
        int lo = 0;
        int hi = list.size();

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (target <= list.get(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}