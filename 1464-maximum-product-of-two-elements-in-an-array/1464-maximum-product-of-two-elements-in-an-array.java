class Solution {
    public int maxProduct(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int a = 0, b = 0;

        for (int num : nums) {
            b = Math.max(b, Math.min(a, num));
            a = Math.max(a, num);
        }

        return (a - 1) * (b - 1);
    }
}