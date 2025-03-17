class Solution {
    public boolean divideArray(int[] nums) {
        return mySol(nums);
    }

    public boolean mySol(int[] nums) {
        int[] counts = new int[501];

        for (int num : nums) counts[num]++;

        for (int count : counts) {
            if (count % 2 == 1) return false;
        }

        return true;
    }
}