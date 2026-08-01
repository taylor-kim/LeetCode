class Solution {
    public boolean predictTheWinner(int[] nums) {
        return mySol(nums);
    }

    public boolean mySol(int[] nums) {
        int n = nums.length;
        long[] scores = new long[2];

        return !play(nums, 0, n - 1, 0, 0, 0);
    }

    private boolean play(int[] nums, int left, int right, int turn, long scoreA, long scoreB) {
        if (left > right) return scoreA < scoreB;

        if (turn == 0) {
            return play(nums, left + 1, right, turn ^ 1, scoreA + nums[left], scoreB)
                && play(nums, left, right - 1, turn ^ 1, scoreA + nums[right], scoreB);
        } else {
            return play(nums, left + 1, right, turn ^ 1, scoreA, scoreB + nums[left])
                || play(nums, left, right - 1, turn ^ 1, scoreA, scoreB + nums[right]);
        }
    }
}