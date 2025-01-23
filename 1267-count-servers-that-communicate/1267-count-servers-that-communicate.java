class Solution {
    public int countServers(int[][] grid) {
        return mySol(grid);
    }

    public int mySol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] numberOfRow = new int[m];
        int[] numberOfCol = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    numberOfRow[i]++;
                    numberOfCol[j]++;
                }
            }
        }

        Set<String> set = new HashSet();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1
                    && (numberOfRow[i] > 1 || numberOfCol[j] > 1)) {
                    set.add(i + "_" + j);
                }
            }
        }

        return set.size();
    }
}