class Solution {
    public int[] sortArray(int[] nums) {
        return mergeSort(nums);
    }

    public int[] mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);

        return nums;
    }

    public void mergeSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;

        int mid = lo + (hi - lo) / 2;

        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        int[] left = new int[mid - lo + 1];
        int[] right = new int[hi - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = nums[lo + i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = nums[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int index = lo;

        while (i < left.length || j < right.length) {
            if (j == right.length || i < left.length && left[i] < right[j]) {
                nums[index] = left[i++];
            } else {
                nums[index] = right[j++];
            }

            index++;
        }
    }
}