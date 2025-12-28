class Solution {
    public int countNegatives(int[][] grid) {
        return others_better(grid);
    }

    public int others_better(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;
        int i = m - 1;
        int j = 0;

        while (i >= 0 && j < n) {
            if (grid[i][j] < 0) {
                ans += n - j;
                i--;
            } else {
                j++;
            }
        }

        return ans;
    }

    public int my_others_better(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;
        int i = m - 1;
        int j = 0;

        while (i >= 0 && j < n) {
            int col = n - 1;
            while (col >= j && grid[i][col] < 0) {
                col--;
            }
            ans += n - col - 1;
            j = col + 1;
            i--;
        }

        return ans;
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