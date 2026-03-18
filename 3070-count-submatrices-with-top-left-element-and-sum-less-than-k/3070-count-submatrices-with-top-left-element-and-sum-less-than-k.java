class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        return official_good(grid, k);
    }

    public int official_good(int[][] grid, int k) {
        int ans = 0;

        int m = grid.length;
        int n = grid[0].length;

        int[] cols = new int[n];

        for (int i = 0; i < m; i++) {
            int rows = 0;
            for (int j = 0; j < n; j++) {
                cols[j] += grid[i][j];

                rows += cols[j];

                if (rows <= k) {
                    ans++;
                }
            }
        }
        
        return ans;
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