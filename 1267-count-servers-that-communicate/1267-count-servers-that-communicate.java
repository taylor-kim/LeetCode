class Solution {
    public int countServers(int[][] grid) {
        return mySol(grid);
    }

    public int mySol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Map<Integer, Integer> numberOfRow = new HashMap();
        Map<Integer, Integer> numberOfCol = new HashMap();

        for (int i = 0; i < m; i++) {
            numberOfRow.put(i, 0);
            for (int j = 0; j < n; j++) {
                numberOfCol.computeIfAbsent(j, k -> 0);

                if (grid[i][j] == 1) {
                    numberOfRow.put(i, numberOfRow.get(i) + 1);
                    numberOfCol.put(j, numberOfCol.get(j) + 1);
                }
            }
        }

        Set<String> set = new HashSet();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1
                    && (numberOfRow.get(i) > 1 || numberOfCol.get(j) > 1)) {
                    set.add(i + "_" + j);
                }
            }
        }

        return set.size();
    }
}