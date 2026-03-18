class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        return mySol(grid, k);
    }

    public int mySol(int[][] grid, int k) {
        int ans = 0;

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int top = i == 0 ? 0 : grid[i - 1][j];
                int left = j == 0 ? 0 : grid[i][j - 1];
                int topLeft = i != 0 && j != 0 ? grid[i - 1][j - 1] : 0;

                grid[i][j] += top + left - topLeft;

                if (grid[i][j] <= k) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
}