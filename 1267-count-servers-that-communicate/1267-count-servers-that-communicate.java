class Solution {
    public int countServers(int[][] grid) {
        return official_track_with_two_arrays(grid);
    }

    public int official_track_with_two_arrays(int[][] grid) {
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

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1
                    && (numberOfRow[i] > 1 || numberOfCol[j] > 1)) {
                    ans++;
                }
            }
        }

        return ans;
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

        Set<Integer> set = new HashSet();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1
                    && (numberOfRow[i] > 1 || numberOfCol[j] > 1)) {
                    set.add(i * n + j);
                }
            }
        }

        return set.size();
    }
}