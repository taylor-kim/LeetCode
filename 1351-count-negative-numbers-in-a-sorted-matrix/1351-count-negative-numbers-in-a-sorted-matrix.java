class Solution {
    public int countNegatives(int[][] grid) {
        return mySol(grid);
    }

    public int mySol(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int index = search(grid[i], -1);

            ans += m - index;
        }

        return ans;
    }

    private int search(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] <= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}