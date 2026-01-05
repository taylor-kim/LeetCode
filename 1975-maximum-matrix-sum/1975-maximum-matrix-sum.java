class Solution {
    public long maxMatrixSum(int[][] matrix) {
        return try_20260105_2(matrix);
    }

    public long try_20260105_2(int[][] matrix) {
        long ans = 0;

        int maxNeg = Integer.MIN_VALUE;
        int countNeg = 0;
        boolean hasZero = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < 0) {
                    maxNeg = Math.max(maxNeg, matrix[i][j]);
                    matrix[i][j] = -matrix[i][j];
                    countNeg++;
                } else if (matrix[i][j] == 0) {
                    hasZero = true;
                }

                ans += matrix[i][j];
            }
        }

        // System.out.println("ans:%d, maxNeg:%d, countNeg:%d".formatted(ans, maxNeg, countNeg));

        if (countNeg % 2 == 1 && !hasZero) {
            ans += maxNeg * 2;
        }

        return ans;
    }

    private int[][] dirs = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };

    public long try_20260105_fail(int[][] matrix) {
        return topdown(matrix, 0, 0, 1, new boolean[matrix.length][matrix.length]);
    }

    public long topdown(int[][] matrix, int r, int c, int sign, boolean[][] visit) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || visit[r][c]) return 0;

        visit[r][c] = true;

        long nonChange = 0;
        long change = 0;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            nonChange = Math.max(nonChange, sign * matrix[r][c] + topdown(matrix, nr, nc, sign, visit));
            change = Math.max(change, -sign * matrix[r][c] + topdown(matrix, nr, nc, -sign, visit));
        }

        return Math.max(nonChange, change);
    }
    
    public long editorial(int[][] matrix) {
        int n = matrix.length;
        int negCount = 0;
        int minAbs = Integer.MAX_VALUE;

        long sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                int abs = Math.abs(value);
                sum += abs;

                minAbs = Math.min(minAbs, abs);

                if (value < 0) {
                    negCount++;
                }
            }
        }

        if (negCount % 2 == 1) {
            sum -= 2 * minAbs;
        }

        return sum;
    }

    public long mySol2(int[][] matrix) {
        int n = matrix.length;
        int negCount = 0;
        int minAbs = Integer.MAX_VALUE;
        int countZero = 0;

        long sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                int abs = Math.abs(value);
                sum += abs;

                minAbs = Math.min(minAbs, abs);

                if (value == 0) {
                    countZero++;
                }

                if (value < 0) {
                    negCount++;
                }
            }
        }

        if (countZero == 0 && negCount % 2 == 1) {
            sum -= 2 * minAbs;
        }

        return sum;
    }

    public long mySol_holding(int[][] matrix) {
        int n = matrix.length;
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        long sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];

                pq.add(new int[] {matrix[i][j], i, j});
            }
        }

        int[][] dirs = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
        };

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int value = data[0];
            int y = data[1];
            int x = data[2];

            Integer targetY = null;
            Integer targetX = null;
            Integer targetValue = null;

            for (int[] dir : dirs) {
                int ny = y + dir[0];
                int nx = x + dir[1];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;

                int nv = matrix[ny][nx];

                // if (value )
            }
        }

        return sum;
    }
}