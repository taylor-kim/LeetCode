class Solution {
    public boolean canSortArray(int[] nums) {
        return mySol2(nums);
    }

    public boolean mySol2(int[] nums) {
        Integer[] arr = new Integer[nums.length];

        for (int i = 0; i < arr.length; i++) arr[i] = nums[i];

        Arrays.sort(arr, (a, b) -> {
            if (Integer.bitCount(a) == Integer.bitCount(b)) {
                return a - b;
            } else {
                return 0;
            }
        });

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }

        return true;
    }

    public boolean mySol_fail(int[] nums) {
        int min = 0;
        int max = 1024;

        int last = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                last = i - 1;
                continue;
            }
            if (nums[i - 1] > nums[i] && Integer.bitCount(nums[i - 1]) != Integer.bitCount(nums[i])) {
                return false;
            }

            // if ()
        }

        return true;
    }
}