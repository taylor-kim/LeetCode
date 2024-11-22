class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        return official_hashmap(matrix);
    }

    public int official_hashmap(int[][] matrix) {
        Map<String, Integer> map = new HashMap();

        for (int[] row : matrix) {
            StringBuilder sb = new StringBuilder();

            for (int c = 0; c < row.length; c++) {
                if (row[0] == row[c]) {
                    sb.append("T");
                } else {
                    sb.append("F");
                }
            }

            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
        }

        int ans = 0;

        for (int count : map.values()) {
            ans = Math.max(ans, count);
        }

        return ans;
    }

    public int official_bf(int[][] matrix) {
        int ans = 0;

        for (int[] row : matrix) {
            int[] flippedRow = new int[row.length];
            int identicalRows = 0;

            for (int c = 0; c < row.length; c++) {
                flippedRow[c] = 1 - row[c];
            }

            for (int[] cand : matrix) {
                if (Arrays.equals(cand, row) || Arrays.equals(cand, flippedRow)) {
                    identicalRows++;
                }
            }

            ans = Math.max(ans, identicalRows);
        }

        return ans;
    }

    public int mySol2_after_topic(int[][] matrix) {
        Map<Integer, Map<Integer, Integer>> rowCounter = new HashMap();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                count(rowCounter.computeIfAbsent(i, k -> new HashMap()), matrix[i][j], 1);
            }
        }

        return mySol2(matrix, 0, rowCounter);
    }

    private int mySol2(int[][] matrix, int c, Map<Integer, Map<Integer, Integer>> rowCounter) {
        if (c >= matrix[0].length) return count(rowCounter);

        int ans = mySol2(matrix, c + 1, rowCounter);

        flip(matrix, c, rowCounter);
        ans = Math.max(ans, mySol2(matrix, c + 1, rowCounter));
        flip(matrix, c, rowCounter);

        return ans;
    }

    private int count(Map<Integer, Map<Integer, Integer>> rowCounter) {
        int rows = 0;

        for (Map<Integer, Integer> counter : rowCounter.values()) {
            if (counter.size() == 1) rows++;
        }

        return rows;
    }

    private void count(Map<Integer, Integer> counter, int number, int delta) {
        counter.put(number, counter.getOrDefault(number, 0) + delta);

        if (counter.get(number) == 0) {
            counter.remove(number);
        }
    }

    private void flip(int[][] matrix, int c, Map<Integer, Map<Integer, Integer>> rowCounter) {
        for (int r = 0; r < matrix.length; r++) {
            int origin = matrix[r][c];
            int flip = (origin + 1) % 2;

            count(rowCounter.get(r), origin, -1);
            count(rowCounter.get(r), flip, 1);

            matrix[r][c] = flip;
        }
    }

    public int mySol_fail(int[][] matrix) {
        return mySol(matrix, 0);
    }

    public int mySol(int[][] matrix, int c) {
        if (c >= matrix[0].length) return count(matrix);

        int ans = mySol(matrix, c + 1);

        flip(matrix, c);
        ans = Math.max(ans, mySol(matrix, c + 1));
        flip(matrix, c);

        return ans;
    }

    private int count(int[][] matrix) {
        int count = 0;

        for (int r = 0; r < matrix.length; r++) {
            boolean same = true;
            for (int c = 1; c < matrix[r].length && same; c++) {
                if (matrix[r][c - 1] != matrix[r][c]) {
                    same = false;
                }
            }

            if (same) count++;
        }

        return count;
    }

    private void flip(int[][] matrix, int c) {
        for (int r = 0; r < matrix.length; r++) {
            matrix[r][c] = (matrix[r][c] + 1) % 2;
        }
    }
}