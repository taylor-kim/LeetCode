class Solution {
    public int minOperations(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        int sum = 0;
        
        for (int num : nums) sum += num;

        return sum % k;
    }
}