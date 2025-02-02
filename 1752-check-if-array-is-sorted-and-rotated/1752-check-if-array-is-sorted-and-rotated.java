class Solution {
    public boolean check(int[] nums) {
        return official_linear(nums);
    }

    public boolean official_linear(int[] nums) {
        int n = nums.length;
        if (n <= 1) return true;

        int inversionCount = 0;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                inversionCount++;
            }
        }

        if (nums[0] < nums[n - 1]) {
            inversionCount++;
        }

        return inversionCount <= 1;
    }

    public boolean mySol2(int[] nums) {
        int index = 0;
        int min = nums[index++];

        while (index < nums.length && nums[index - 1] <= nums[index]) {
            index++;
        }

        if (index < nums.length && min < nums[index]) {
            return false;
        }

        index++;

        while (index < nums.length && nums[index - 1] <= nums[index] && nums[index] <= min) {
            index++;
        }

        return index >= nums.length;
    }

    public boolean mySol(int[] nums) {
        int min = nums[0];
        boolean foundRotated = false;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                if (foundRotated) return false;
                foundRotated = true;
            }

            if (foundRotated && min < nums[i]) {
                return false;
            }
        }

        return true;
    }
}