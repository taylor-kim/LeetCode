class Solution {
    public int minOperations(int[][] grid, int x) {
        return mySol(grid, x);
    }

    public int mySol(int[][] grid, int x) {
        int n = grid.length;
        int m = grid[0].length;
        int[] arr = new int[n * m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i * m + j] = grid[i][j];
            }
        }

        Arrays.sort(arr);

        int mid = arr.length / 2;
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            int diff = Math.abs(arr[mid] - arr[i]);

            if (diff == 0) continue;

            if (diff % x != 0) return -1;

            ans += diff / x;
        }

        return ans;
    }
}