class Solution {
    public int[][] sortMatrix(int[][] grid) {
        return mySol(grid);
    }

    public int[][] mySol(int[][] grid) {
        int n = grid.length;

        sort(grid, 0, 0, false);

        for (int i = 1; i < n; i++) {
            sort(grid, i, 0, false);
            sort(grid, 0, i, true);
        }

        return grid;
    }

    private void sort(int[][] grid, int y, int x, boolean asc) {
        int n = grid.length;

        if (y >= n || x >= n || y < 0 || x < 0) return;

        List<Integer> list = new ArrayList();

        int i = y;
        int j = x;

        while (i < n && j < n) {
            list.add(grid[i++][j++]);
        }

        if (asc) {
            Collections.sort(list);
        } else {
            Collections.sort(list, Comparator.reverseOrder());
        }

        i = y;
        j = x;

        int index = 0;

        while (i < n && j < n) {
            grid[i++][j++] = list.get(index++);
        }
    }
}