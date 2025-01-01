class Solution {
    public void nextPermutation(int[] nums) {
        tryAgain_20250101(nums);
    }

    public void tryAgain_20250101(int[] nums) {
        int n = nums.length;
        boolean found = false;
        int left = -1;
        int right = 0;

        for (int length = 2; length <= n && !found; length++) {
            for (int i = n - 1; i > n - 1 - length && !found; i--) {
                for (int j = i - 1; j > n - 1 - length && !found; j--) {
                    if (nums[j] < nums[i]) {
                        swap(nums, i, j);

                        left = j;
                        right = i;

                        found = true;
                    }
                }
            }
        }

        // System.out.println(String.format("left:%d, right:%d", left, right));
        // Arrays.sort(nums, left + 1, n);
        reverse(nums, left + 1);

        // if (!found) {
        //     Arrays.sort(nums);
        // } else {
            
        // }
    }

    private void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;

        while (left < right) {
            swap(nums, left++, right--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}