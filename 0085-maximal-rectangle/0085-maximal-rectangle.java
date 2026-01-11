class Solution {
    public int maximalRectangle(char[][] matrix) {
        return others_nice_sol(matrix);
    }

    public int others_nice_sol(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] heights = new int[n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j]++;
                }
            }

            ans = Math.max(ans, calcMaxRec(heights));
        }

        return ans;
    }

    public int calcMaxRec(int[] heights) {
        int n = heights.length;
        int max = 0;
        Stack<Integer> stack = new Stack();

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || heights[stack.peek()] >= heights[i])) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;

                max = Math.max(max, h * w);
            }

            stack.push(i);
        }

        return max;
    }

    public int test(char[][] matrix) {
        int[] h = {1,2,3,4,5};
        largestRectangleArea(h);

        return 0;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || heights[stack.peek()] >= heights[i])) {
                System.out.println(stack);

                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;

                System.out.println(String.format("h:%d, w:%d", height, width));

                maxArea = Math.max(maxArea, width * height);
            }
            stack.push(i);
        }
        return maxArea;
    }

    private void dfs(int startR, int startC, int r, int c, char[][] matrix, int[] ans) {
        if (r >= matrix.length || r < startR || c >= matrix[0].length || c < startC) return;

        if (matrix[r][c] == '1') {
            for (int i = startR; i < r; i++) {

            }
        }
    }

    public int mySol_hold(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] rowSum = new int[m][n + 1];
        int[][] colSum = new int[n][m + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowSum[i][j + 1] = 0;
                    colSum[j][i + 1] = 0;
                } else {
                    rowSum[i][j + 1] = rowSum[i][j] + matrix[i][j];
                    colSum[j][i + 1] = colSum[j][i] + matrix[i][j];
                }
            }
        }

        return 0;
    }

    private int dfs_hold(int r, int c, int[][] pSum, char[][] matrix) {
        if (r >= matrix.length || r < 0 || c >= matrix[0].length || c < 0) return 0;

        if (matrix[r][c] == '0') return 0;

        return 0;
    }
}