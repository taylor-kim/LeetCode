class Solution {
    public int minimumIndex(List<Integer> nums) {
        return mySol2(nums);
    }

    public int mySol2(List<Integer> nums) {
        int n = nums.size();

        int x = findX(nums);

        int[] counter = new int[n + 1];

        for (int i = 0; i < n; i++) {
            counter[i + 1] = counter[i] + (nums.get(i) == x ? 1 : 0);
        }

        // System.out.println(x);
        // System.out.println(Arrays.toString(counter));

        for (int i = 0; i < n - 1; i++) {
            int leftLength = i + 1;
            int rightLength = n - leftLength;

            // System.out.println(String.format("i:%d, leftInfo : count:%d, expected:%d, rightInfo : count:%d, expected:%d",
            //     i, counter[i + 1], (leftLength / 2) + 1, counter[n] - counter[i], (rightLength / 2) + 1
            // ));

            if (counter[i + 1] >= (leftLength / 2) + 1
                && counter[n] - counter[i + 1] >= (rightLength / 2) + 1) {
                return i;
            }
        }

        return -1;
    }

    public int mySol_bs_hold(List<Integer> nums) {
        int n = nums.size();

        int x = findX(nums);

        int[] counter = new int[n + 1];

        for (int i = 0; i < n; i++) {
            counter[i + 1] = counter[i];

            if (nums.get(i) == x) {
                counter[i + 1]++;
            }
        }

        int lo = 0;
        int hi = n - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            // if (findX(nums, 0, mid) == findX())
        }

        return lo;
    }

    private int findX(List<Integer> nums) {
        int target = 0;
        int count = 0;

        for (int num : nums) {
            if (target == num) {
                count++;
            } else if (count-- == 0) {
                target = num;
                count = 1;
            }
        }

        return target;
    }

    private int findX(List<Integer> nums, int start, int until) {
        int target = 0;
        int count = 0;

        for (int i = start; i <= Math.min(until, nums.size()); i++) {
            int num = nums.get(i);
            if (target == num) {
                count++;
            } else if (count-- == 0) {
                target = num;
                count = 1;
            }
        }

        return target;
    }
}