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

        int i = lo;
        int j = mid + 1;
        int index = 0;

        int[] sorted = new int[hi - lo + 1];

        while (i <= mid && j <= hi) {
            if (nums[i] < nums[j]) {
                sorted[index] = nums[i++];
            } else {
                sorted[index] = nums[j++];
            }
            index++;
        }

        while (i <= mid) {
            sorted[index++] = nums[i++];
        }

        while (j <= hi) {
            sorted[index++] = nums[j++];
        }

        for (int k = lo; k <= hi; k++) {
            nums[k] = sorted[k - lo];
        }
    }
}