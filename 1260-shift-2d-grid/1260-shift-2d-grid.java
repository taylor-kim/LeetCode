class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        return mySol(grid, k);
    }

    public List<List<Integer>> mySol(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int size = m * n;

        k %= size;

        int[][] arr = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                int shifted = (index + k) % size;
                arr[shifted / n][shifted % n] = grid[i][j];
            }
        }

        List<List<Integer>> ans = new ArrayList();

        for (int i = 0; i < m; i++) {
            ans.add(Arrays.stream(arr[i]).boxed().collect(Collectors.toList()));
        }

        return ans;
    }
}