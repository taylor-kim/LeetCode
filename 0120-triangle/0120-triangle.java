class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        return mySol2(triangle);
    }

    public int mySol2(List<List<Integer>> triangle) {
        int r = triangle.size();
        int c = triangle.get(r - 1).size();

        int[][] dp = new int[r + 1][c + 1];

        // dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]);

        for (int i = r - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        return dp[0][0];
    }

    public int mySol(List<List<Integer>> triangle) {
        int r = triangle.size();
        int c = triangle.get(r - 1).size();
        
        return topdown(0, 0, triangle, new Integer[r][c]);
    }

    public int topdown(int row, int col, List<List<Integer>> triangle, Integer[][] memo) {
        if (row >= triangle.size()) return 0;

        List<Integer> list = triangle.get(row);

        if (col >= list.size()) return (int)1e4 + 1;

        if (memo[row][col] != null) return memo[row][col];

        int a = topdown(row + 1, col, triangle, memo);
        int b = topdown(row + 1, col + 1, triangle, memo);

        return memo[row][col] = list.get(col) + Math.min(a, b);
    }
}