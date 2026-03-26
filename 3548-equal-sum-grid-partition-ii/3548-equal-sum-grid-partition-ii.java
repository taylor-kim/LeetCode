class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        return mySol(grid);
    }

    public boolean mySol(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long total = 0;
        long[] rowSum = new long[n];
        long[] colSum = new long[m];

        Map<Long, Integer> rowMap1 = new HashMap();
        Map<Long, Integer> colMap1 = new HashMap();

        Map<Long, Integer> rowMap2 = new HashMap();
        Map<Long, Integer> colMap2 = new HashMap();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = grid[i][j];

                total += num;
                rowSum[i] += num;
                colSum[j] += num;

                modify(rowMap2, num, 1);
                modify(colMap2, num, 1);
            }
        }

        for (int i = 0; i < n; i++) {
            long diff = (total - rowSum[i]) - rowSum[i];

            if (diff == 0) return true;

            for (int col : grid[i]) {
                modify(rowMap1, col, 1);
                modify(rowMap2, col, -1);
            }

            // System.out.println("diff:%d".formatted(diff));
            // System.out.println(rowMap1);
            // System.out.println(rowMap2 + "\n");

            if (diff > 0) {
                if (rowMap2.containsKey(diff) && 
                    (i + 1 != n - 1 || 
                        (grid[i + 1][0] == diff || grid[i + 1][m - 1] == diff)
                    )
                ) return true;
            } else {
                if (rowMap1.containsKey(-diff) && 
                    (i > 0 ||
                        (grid[i][0] == -diff || grid[i][m - 1] == -diff)
                    )) return true;
            }

            if (i + 1 < n) {
                rowSum[i + 1] += rowSum[i];
            }
        }

        for (int j = 0; j < m; j++) {
            long diff = (total - colSum[j]) - colSum[j];

            System.out.println("total:%d, colSum[%d]:%d, diff:%d".formatted(total, j, colSum[j], diff));

            if (diff == 0) return true;

            for (int i = 0; i < n; i++) {
                int row = grid[i][j];

                modify(colMap1, row, 1);
                modify(colMap2, row, -1);
            }

            if (diff > 0) {
                if (colMap2.containsKey(diff) &&
                    (j + 1 != m - 1 ||
                        (grid[0][j + 1] == -diff || grid[n - 1][j] == -diff)
                    )
                ) return true;
            } else {
                if (colMap1.containsKey(-diff) && 
                    (j > 0 ||
                        (grid[0][j] == diff || grid[n - 1][j + 1] == diff)
                    )
                ) return true;
            }

            if (j + 1 < m) {
                colSum[j + 1] += colSum[j];
            }
        }

        return false;
    }

    private void modify(Map<Long, Integer> map, long num, int delta) {
        map.put(num, map.getOrDefault(num, 0) + delta);

        if (map.get(num) == 0) {
            map.remove(num);
        }
    }
}