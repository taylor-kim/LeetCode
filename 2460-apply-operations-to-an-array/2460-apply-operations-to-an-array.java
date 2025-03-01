class Solution {
    public int[] applyOperations(int[] nums) {
        return official_onepass(nums);
    }

    public int[] official_onepass(int[] nums) {
        int writeIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] == nums[i + 1] && nums[i] != 0) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }

            if (nums[i] != 0) {
                if (i != writeIndex) {
                    int temp = nums[i];
                    nums[i] = nums[writeIndex];
                    nums[writeIndex] = temp;
                }
                writeIndex++;
            }
        }

        return nums;
    }

    public int[] try_onepass(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int index = 0;

        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                ans[index] = nums[i];
            } else if (nums[i] == 0) {
                continue;
            } else if (nums[i] == nums[i + 1]) {
                ans[index++] = nums[i] * 2;
                nums[i + 1] = 0;
            } else if (nums[i] > 0) {
                ans[index++] = nums[i];
            }
        }

        return ans;
    }

    public int[] mySol(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }

        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        while (index < nums.length) {
            nums[index++] = 0;
        }

        return nums;
    }
}