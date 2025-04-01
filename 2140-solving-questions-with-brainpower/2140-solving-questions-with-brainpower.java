class Solution {
    public long mostPoints(int[][] questions) {
        return mySol(questions);
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