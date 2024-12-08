class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        return editorial_bs(nums, maxOperations);
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
            // totalOp += (num + (max - 1)) / max - 1;
            // totalOp += Math.max(0, num / max - (num % max == 0 ? 1 : 0));
            totalOp += num / max - (num % max == 0 ? 1 : 0);

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

    private boolean canMake(int[] nums, int max, int op) {
        int multiple = 1;
        int start = leftmost(nums, 0, nums.length, (max * multiple) + 1);

        if (start >= nums.length) return true;

        int end = 0;

        while (op > 0 && end < nums.length) {
            end = leftmost(nums, start, nums.length, (max * (multiple + 1)) + 1);

            op -= (end - start) * multiple;

            if (op < 0) {
                return false;
            }

            start = end;
            multiple++;
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