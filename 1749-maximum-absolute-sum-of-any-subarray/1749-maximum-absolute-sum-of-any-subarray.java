class Solution {
    public int maxAbsoluteSum(int[] nums) {
        return tryAgain_20250303(nums);
    }

    public int tryAgain_20250303(int[] nums) {
        int n = nums.length;
        int[] dpNeg = new int[n];
        int[] dpPos = new int[n];

        int sum = nums[0];

        if (nums[0] < 0) {
            dpNeg[0] = nums[0];
        } else {
            dpPos[0] = nums[0];
        }

        int ans = Math.abs(nums[0]);

        for (int i = 1; i < n; i++) {
            if (nums[i] < 0) {
                dpNeg[i] = nums[i] + dpNeg[i - 1];
                dpPos[i] = Math.max(dpPos[i - 1] + nums[i], 0);
            } else {
                dpPos[i] = nums[i] + dpPos[i - 1];
                dpNeg[i] = Math.min(dpNeg[i - 1] + nums[i], 0);
            }

            // System.out.println(Arrays.toString(dpNeg));
            // System.out.println(Arrays.toString(dpPos) + "\n");

            ans = Math.max(ans, Math.max(dpPos[i], Math.abs(dpNeg[i])));
        }

        return ans;
    }
}