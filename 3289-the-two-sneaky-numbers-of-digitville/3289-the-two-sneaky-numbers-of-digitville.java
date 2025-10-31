class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        return mySol(nums);
    }

    public int[] mySol(int[] nums) {
        int[] ans = {-1, -1};

        int n = nums.length;

        int[] freq = new int[n - 1];

        for (int num : nums) {
            if (++freq[num] > 1) {
                if (ans[0] < 0) {
                    ans[0] = num;
                } else {
                    ans[1] = num;
                    break;
                }
            }
        }

        return ans;
    }
}