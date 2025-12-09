class Solution {
    public int specialTriplets(int[] nums) {
        return try_editorial_bs(nums);
    }

    public int try_editorial_bs(int[] nums) {
        int n = nums.length;

        Map<Integer, List<Integer>> freq = new HashMap();
        
        for (int i = 0; i < n; i++) {
            freq.computeIfAbsent(nums[i], k -> new ArrayList()).add(i);
        }

        int ans = 0;
        int mod = (int)1e9 + 7;

        for (int j = 1; j < n - 1; j++) {
            int num = nums[j];

            int target = num * 2;

            List<Integer> ikList = freq.get(target);

            if (ikList != null && ikList.size() > 1 && ikList.get(0) < j) {
                int indexOfK = leftmost(ikList, j + 1);

                int leftCount = indexOfK;
                int rightCount = ikList.size() - leftCount;

                // System.out.println("ikList:%s, j:%d, indexOfK:%d, lc:%d, rc:%d".formatted(ikList, j, indexOfK, leftCount, rightCount));

                if (num == 0) leftCount--;

                ans = (int)((ans + (long)leftCount * rightCount) % mod);
            }
        }

        return ans;
    }

    public int editorial(int[] nums) {
        int mod = (int)1e9 + 7;
        Map<Integer, Integer> currentCounter = new HashMap();
        Map<Integer, Integer> totalCounter = new HashMap();

        for (int num : nums) {
            totalCounter.put(num, totalCounter.getOrDefault(num, 0) + 1);
        }

        long ans = 0;

        for (int num : nums) {
            int target = num * 2;

            int leftCount = currentCounter.getOrDefault(target, 0);

            currentCounter.put(num, currentCounter.getOrDefault(num, 0) + 1);

            int rightCount = totalCounter.getOrDefault(target, 0) - currentCounter.getOrDefault(target, 0);

            ans = (ans + (long)leftCount * rightCount) % mod;
        }

        return (int)ans;
    }

    public int hint_fail(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> freqPrev = new HashMap();
        Map<Integer, Integer> freqNext = new HashMap();

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            freqPrev.put(num, 0);
            freqPrev.put(num * 2, freqPrev.getOrDefault(num * 2, 0) + 1);

            int j = n - i - 1;

            int num2 = nums[j];

            freqNext.put(num2, 0);
            freqNext.put(num2 * 2, freqNext.getOrDefault(num2 * 2, 0) + 1);
        }

        int ans = 0;
        int mod = (int)1e9 + 7;

        for (int num : nums) {

        }

        return ans;
    }

    public int mySol_fail(int[] nums) {
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

            int rightK = ikList.getLast();

            if (rightK - i <= 1) continue;

            int half = num / 2;

            List<Integer> jList = freq.getOrDefault(half, Collections.emptyList());

            int startJ = leftmost(jList, i + 1);
            int endJ = leftmost(jList, rightK);

            int leftK = leftmost(ikList, endJ + 1);

            System.out.println("jList:%s, startJ:%d, endJ:%d".formatted(jList, startJ, endJ));
            System.out.println("ikList:%s, leftK:%d, rightK:%d".formatted(ikList, leftK, rightK));

            ans = (ans + (endJ - startJ) * (rightK - leftK + 1)) % mod;
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