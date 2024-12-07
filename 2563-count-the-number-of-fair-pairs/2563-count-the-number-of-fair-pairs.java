class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        return official_good_bs(nums, lower, upper);
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

        if (lo >= nums.length || nums[lo] < target) return -1;

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

        if (lo < 0 || nums[lo] > target) return -1;

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