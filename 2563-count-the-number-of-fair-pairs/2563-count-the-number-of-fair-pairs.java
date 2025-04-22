class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        return mySol2(nums, lower, upper);
    }

    public long try_20250419_treemap_fail(int[] nums, int lower, int upper) {
        Arrays.sort(nums);

        TreeMap<Integer, int[]> map = new TreeMap();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (map.containsKey(num)) {
                map.get(num)[1] = i;
            } else {
                map.put(num, new int[] {i, i});
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int key : map.keySet()) {
            sb
            .append(key)
            .append(":")
            .append(String.format("[%d, %d], ", map.get(key)[0], map.get(key)[1]));
        }

        System.out.println(sb.toString());

        int ans = 0;

        for (int num : nums) {
            Integer leftKey = map.floorKey(lower - num);

            if (leftKey == null || leftKey > num) continue;

            Integer rightKey = map.ceilingKey(upper - num);

            if (rightKey == null || rightKey < num) continue;

            System.out.println(String.format("num:%d, leftKey:%d, rightKey:%d, rIndex:%d, lIndex:%d",
            num, leftKey, rightKey, map.get(rightKey)[1], map.get(leftKey)[0]));

            ans += (map.get(rightKey)[1] - map.get(leftKey)[0]) + 1;
        }

        return ans;
    }

    public long official_good_bs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return lower_bound(nums, upper + 1) - lower_bound(nums, lower);
    }

    // Calculate the number of pairs with sum less than `value`.
    private long lower_bound(int[] nums, int value) {
        int left = 0, right = nums.length - 1;
        long result = 0;
        while (left < right) {
            int sum = nums[left] + nums[right];
            // If sum is less than value, add the size of window to result and move to the
            // next index.
            if (sum < value) {
                result += (right - left);
                left++;
            } else {
                // Otherwise, shift the right pointer backwards, until we get a valid window.
                right--;
            }
        }

        return result;
    }

    public long mySol2(int[] nums, int lower, int upper) {
        // 0, 1, 4, 4, 5, 7
        Arrays.sort(nums);

        // System.out.println(Arrays.toString(nums) + "\n");

        long ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int targetLo = lower - nums[i];
            int lo = leftmost(nums, targetLo, i + 1);

            // System.out.println(String.format("i:%d, targetLo:%d, lo:%d", i, targetLo, lo));

            if (lo < 0) continue;

            int targetHi = upper - nums[i];
            int hi = rightmost(nums, targetHi, i + 1);

            // System.out.println(String.format("i:%d, targetHi:%d, hi:%d", i, targetHi, hi));

            if (hi < 0) continue;

            // if (lo > hi) continue;

            ans += hi - lo + 1;
        }

        return ans;
    }

    private int leftmost(int[] nums, int target, int start) {
        int lo = start;
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (target <= nums[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        // if (lo >= nums.length || nums[lo] < target) return -1;

        if (lo >= nums.length) return -1;

        return lo;
    }

    private int rightmost(int[] nums, int target, int start) {
        int lo = start;
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (target < nums[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        lo--;

        // if (lo < 0 || nums[lo] > target) return -1;

        if (lo < 0) return -1;

        return lo;
    }

    public long mySol_fail(int[] nums, int lower, int upper) {
        Stack<Integer> stack = new Stack();

        long ans = 0;

        for (int i = 0; i < nums.length; i++) {
            while (stack.size() > 0 && stack.peek() >= nums[i]) {

            }

            stack.push(nums[i]);

            if (stack.size() >= 2) {
                ans += stack.size() - 1;
            }
        }

        return ans;
    }
}