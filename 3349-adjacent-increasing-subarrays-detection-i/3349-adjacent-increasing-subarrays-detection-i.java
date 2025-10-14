class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        return mySol(nums, k);
    }

    public boolean mySol(List<Integer> nums, int k) {
        int prev = Integer.MIN_VALUE;
        int length = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (isInc(i, nums, k) && isInc(i + k, nums, k)) return true;
        }

        return false;
    }

    private boolean isInc(int start, List<Integer> nums, int k) {
        int prev = Integer.MIN_VALUE;
        int length = 0;

        for (int i = start; i < start + k && i < nums.size(); i++) {
            int num = nums.get(i);

            if (prev < num) {
                length++;
            } else {
                length = 1;
            }

            if (length == k) {
                // System.out.println(String.format("start:%d, k:%d, i:%d", start, k, i));
                return true;
            }

            prev = num;
        }

        return false;
    }
}