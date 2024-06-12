class Solution {
    public void sortColors(int[] nums) {
        retry_fail_20240612(nums);
    }

    public void retry_fail_20240612(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        int index = 0;

        while (index <= right) {
            if (nums[index] == 0) {
                nums[index] = nums[left];
                nums[left++] = 0;
            } else if (nums[index] == 2) {
                nums[index--] = nums[right];
                nums[right--] = 2;
            }
            index++;
        }
    }

    public void othersOnePass(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int index = 0;

        while (index <= hi) {
            if (nums[index] == 0) {
                nums[index] = nums[lo];
                nums[lo++] = 0;
            } else if (nums[index] == 2) {
                nums[index--] = nums[hi];
                nums[hi--] = 2;
            }
            index++;
        }
    }

    public void mySolTowPass(int[] nums) {
        int[] count = new int[3];

        for (int num : nums) {
            count[num]++;
        }

        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            while (count[index] == 0) {
                index++;
            }

            count[index]--;

            nums[i] = index;
        }
    }

    public void bruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }
}