class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        return mySol(nums, k, numOperations);
    }

    public int mySol2_fail(int[] nums, int k, int op) {
        TreeMap<Integer, Integer> map = new TreeMap();

        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        int ans = 0;

        for (int key : map.keySet()) {
            Integer from = map.ceilingKey(key - k);
            Integer to = map.floorKey(key + k);

            System.out.println(String.format("key:%d, from:%d, to:%d", key, from, to));

            List<Integer> list = new ArrayList();

            while (from != null && from <= to) {
                int count = map.get(from);

                int index = Collections.binarySearch(list, count);

                if (index < 0) index = -(index + 1);

                if (index == list.size()) {
                    list.add(count);
                } else {
                    list.add(index, count);
                }

                from = map.higherKey(from);
            }

            int sum = 0;

            for (int i = list.size() - 1, times = op; i >= 0 && times > 0; i--, times--) {
                sum += list.get(i);
            }

            ans = Math.max(ans, sum);
        }

        return ans;
    }

    public int mySol(int[] nums, int k, int op) {
        int n = nums.length;

        if (n == 1) return 1;
        
        Arrays.sort(nums);

        Map<Integer, Integer> map = new HashMap();

        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int leftIncludeCurrent = Math.min(i - leftmost(nums, nums[i] - k - k) + 1, op);
            int rightIncludeCurrent = Math.min(rightmost(nums, nums[i] + k + k) - i + 1, op);

            int left = nums[i] - k;
            int right = nums[i] + k;

            int l = leftmost(nums, left);
            int r = rightmost(nums, right);

            int count = Math.min(r - l + 1 - map.get(nums[i]), op);

            ans = Math.max(ans, count + map.get(nums[i]));
            ans = Math.max(ans, leftIncludeCurrent);
            ans = Math.max(ans, rightIncludeCurrent);

            // System.out.println(String.format("num:%d, l:%d, r:%d", nums[i], l, r));
        }

        return ans;
    }

    private int leftmost(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (target <= nums[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private int rightmost(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }
}