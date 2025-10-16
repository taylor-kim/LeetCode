class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        return official(nums, value);
    }

    public int official(int[] nums, int value) {
        int[] freq = new int[value];

        for (int num : nums) {
            int v = ((num % value) + value) % value;
            freq[v]++;
        }

        int mex = 0;

        while (freq[mex % value] > 0) {
            freq[mex % value]--;
            mex++;
        }

        return mex;
    }

    public int mySol_fail(int[] nums, int value) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            if (num < 0) {
                int quotient = Math.abs(num / value);
                num += (quotient + 1) * value;
            }
            nums[i] = num % value;
        }

        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));

        int missing = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == missing) {
                missing++;
            } else if (nums[i] > missing) {
                return missing;
            }
        }

        return missing;
    }
}