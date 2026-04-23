class Solution {
    public long[] distance(int[] nums) {
        return official(nums);
    }

    public long[] official(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            groups.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        long[] res = new long[n];
        for (List<Integer> group : groups.values()) {
            long total = 0;
            for (int idx : group) {
                total += idx;
            }
            long prefixTotal = 0;
            int sz = group.size();
            for (int i = 0; i < sz; i++) {
                int idx = group.get(i);
                res[idx] = total - prefixTotal * 2 + (long) idx * (2 * i - sz);
                prefixTotal += idx;
            }
        }
        return res;
    }

    public long[] mySol2_fail(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];

        Map<Integer, List<Long>> pSumMap = new HashMap();

        for (int i = 0; i < n; i++) {
            
        }

        return ans;
    }

    /**
    [1,3,1,1,2,5,1]
     0, 2, 3, 6
     0, 2, 5, 11
     */

    public long[] mySol_fail(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];

        Map<Integer, Long> sums = new HashMap();

        for (int i = 0; i < n; i++) {
            sums.put(nums[i], sums.getOrDefault(nums[i], 0L) + nums[i]);
        }

        for (int i = 0; i < n; i++) {
            ans[i] = sums.get(nums[i]) - nums[i];
        }

        return ans;
    }
}