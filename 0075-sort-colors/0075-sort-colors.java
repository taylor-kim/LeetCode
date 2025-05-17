class Solution {
    public void sortColors(int[] nums) {
        mySol(nums);
    }

    public void mySol(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        for (int i = 0; i < nums.length && i <= right; i++) {
            if (nums[i] == 0) {
                swap(nums, left++, i);
            } else if (nums[i] == 2) {
                swap(nums, right--, i--);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}