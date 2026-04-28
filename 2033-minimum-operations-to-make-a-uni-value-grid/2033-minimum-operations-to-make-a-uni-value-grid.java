class Solution {
    public int minOperations(int[][] grid, int x) {
        return mySol_improved(grid, x);
    }

    public int mySol_improved(int[][] grid, int x) {
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

        for (int i = 0; i < mid; i++) {
            int diff1 = arr[mid] - arr[i];

            if (diff1 % x != 0) return -1;

            int diff2 = arr[arr.length - i - 1] - arr[mid];

            if (diff2 % x != 0) return -1;

            ans += diff1 / x;
            ans += diff2 / x;
        }

        return ans;
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