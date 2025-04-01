class Solution {
    public long mostPoints(int[][] questions) {
        return try_bottomup(questions);
    }

    public long try_bottomup(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], questions[i][0] + dp[ Math.min(i + questions[i][1] + 1, n) ]);
        }

        return dp[0];
    }

    public long mySol(int[][] questions) {
        return topdown(questions, 0, new Long[questions.length]);
    }

    private long topdown(int[][] questions, int index, Long[] memo) {
        if (index >= questions.length) return 0;

        if (memo[index] != null) return memo[index];

        long include = questions[index][0] + topdown(questions, index + questions[index][1] + 1, memo);
        long exclude = topdown(questions, index + 1, memo);

        return memo[index] = Math.max(include, exclude);
    }
}