class Solution {
    public long maxMatrixSum(int[][] matrix) {
        return editorial(matrix);
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