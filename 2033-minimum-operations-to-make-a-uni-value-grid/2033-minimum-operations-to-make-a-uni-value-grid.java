class Solution {
    public int minOperations(int[][] grid, int x) {
        return mySol(grid, x);
    }

    public int mySol(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;

        int[] datas = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                datas[(i * n + j)] = grid[i][j];
            }
        }

        Arrays.sort(datas);

        int mid = datas.length / 2 - (datas.length % 2 == 0 ? 1 : 0);

        int ans = 0;

        for (int i = 0; i <= mid; i++) {
            if ((datas[mid] - datas[i]) % x != 0) return -1;

            if ((datas[datas.length - i - 1] - datas[mid]) % x != 0) return -1;

            ans += (datas[mid] - datas[i]) / x;
            ans += (datas[datas.length - i - 1] - datas[mid]) / x;
        }

        return ans;
    }
}