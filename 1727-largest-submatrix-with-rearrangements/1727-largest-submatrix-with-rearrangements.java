class Solution {
    public int largestSubmatrix(int[][] matrix) {
        return official_no_sort(matrix);
    }

    public int official_no_sort(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int ans = 0;

        List<int[]> prevHeights = new ArrayList();

        for (int i = 0; i < m; i++) {
            List<int[]> heights = new ArrayList();
            boolean[] seen = new boolean[n];

            for (int[] data : prevHeights) {
                int col = data[0];
                int h = data[1];

                if (matrix[i][col] == 1) {
                    heights.add(new int[] {col, h + 1});
                    seen[col] = true;
                }
            }

            for (int j = 0; j < n; j++) {
                if (!seen[j] && matrix[i][j] == 1) {
                    heights.add(new int[] {j, 1});
                }
            }

            for (int j = 0; j < heights.size(); j++) {
                ans = Math.max(ans, heights.get(j)[1] * (j + 1));
            }

            prevHeights = heights;
        }

        return ans;
    }

    public int try_20260317(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int ans = 0;

        for (int i = 0; i < m; i++) {
            Integer[] cols = new Integer[n];

            for (int j = 0; j < n; j++) {
                int top = i == 0 ? 0 : matrix[i - 1][j];
                matrix[i][j] += matrix[i][j] != 0 ? top : matrix[i][j];

                cols[j] = matrix[i][j];
            }

            Arrays.sort(cols, Comparator.reverseOrder());

            int h = Integer.MAX_VALUE;

            for (int j = 0; j < n && cols[j] > 0; j++) {
                h = Math.min(h, cols[j]);
                ans = Math.max(ans, h * (j + 1));
            }
        }

        return ans;
    }
}