class Solution {
    public boolean predictTheWinner(int[] nums) {
        return improve_mySol_with_gemini(nums);
    }

    public boolean improve_mySol_with_gemini(int[] nums) {
        int n = nums.length;

        return maxDiff(nums, 0, n - 1, new Integer[n][n]) >= 0;
    }

    private int maxDiff(int[] nums, int left, int right, Integer[][] memo) {
        if (left > right) return 0;

        if (memo[left][right] != null) return memo[left][right];

        int pickLeft = nums[left] - maxDiff(nums, left + 1, right, memo);
        int pickRight = nums[right] - maxDiff(nums, left, right - 1, memo);

        return memo[left][right] = Math.max(pickLeft, pickRight);
    }

    public boolean mySol_20260801(int[] nums) {
        int n = nums.length;

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