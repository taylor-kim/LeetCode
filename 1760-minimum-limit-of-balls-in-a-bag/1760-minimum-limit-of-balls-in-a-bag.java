class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        return hint(nums, maxOperations);
    }

    public int editorial_bs(int[] nums, int maxOp) {
        int lo = 1;
        int hi = 0;

        for (int num : nums) hi = Math.max(hi, num);

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (isPossible(nums, mid, maxOp)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean isPossible(int[] nums, int max, int op) {
        int totalOp = 0;

        for (int num : nums) {
            totalOp += (num + (max - 1)) / max - 1;
            // totalOp += Math.max(0, num / max - (num % max == 0 ? 1 : 0));
            // totalOp += num / max - (num % max == 0 ? 1 : 0);

            if (op < totalOp) return false;
        }

        return true;
    }

    public int hint(int[] nums, int maxOp) {
        Arrays.sort(nums);

        int lo = 1;
        int hi = nums[nums.length - 1];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canMake(nums, mid, maxOp)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean canMake2(int[] nums, int max, int op) {
        int index = nums.length - 1;
        int value = nums[index];
        
        while (op >= 0 && index >= 0) {
            if (nums[index] <= max) return true;

            op -= (nums[index--] + max - 1) / max - 1;

            if (op < 0) return false;
        }

        return index < 0 ? true : nums[index] <= max;
    }

    private boolean canMake(int[] nums, int max, int op) {
        int multiple = Math.max((nums[0] + max - 1) / max - 1, 1);
        // int multiple = 1;

        if (multiple > op) return false;

        int start = leftmost(nums, 0, nums.length, (max * multiple) + 1);

        if (start >= nums.length) {
            return true;
        }

        int end = 0;

        while (op > 0 && end < nums.length) {
            end = leftmost(nums, start, nums.length, (max * (multiple + 1)) + 1);

            op -= (end - start) * multiple;

            if (op < 0) {
                return false;
            }

            start = end;

            if (start < nums.length) {
                multiple = Math.max((nums[start] + max - 1) / max - 1, 1);
            }
        }

        return true;
    }

    private int leftmost(int[] nums, int lo, int hi, int target) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public int mySol_fail(int[] nums, int maxOp) {
        List<Integer> list = new ArrayList();

        for (int num : nums) list.add(num);

        Collections.sort(list);

        // System.out.println(list);

        while (maxOp-- > 0) {
            int target = list.remove(list.size() - 1);

            int a = 0;

            if (maxOp > 0 && target % 2 == 1) {
                a = target / 3;
            } else {
                a = target / 2;
            }

            int b = target - a;

            insert(list, a);
            insert(list, b);

            // System.out.println(list);
        }

        return list.get(list.size() - 1);
    }

    private void insert(List<Integer> list, int value) {
        int index = Collections.binarySearch(list, value);

        if (index < 0) index = -(index + 1);

        if (index < list.size()) {
            list.add(index, value);
        } else {
            list.add(value);
        }
    }
}