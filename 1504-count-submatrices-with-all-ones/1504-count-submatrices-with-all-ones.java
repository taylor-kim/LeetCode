class Solution {
    public int numSubmat(int[][] mat) {
        return others_stack(mat);
    }

    public int others_stack(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] h = new int[n];

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                h[j] = mat[i][j] == 0 ? 0 : h[j] + 1;
            }

            ans += count(h);
        }

        return ans;
    }

    private int count(int[] h) {
        int n = h.length;
        int res = 0;
        int[] sum = new int[n];

        Stack<Integer> stack = new Stack();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && h[stack.peek()] >= h[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                sum[i] = h[i] * (i + 1);
            } else {
                int p = stack.peek();
                sum[i] = h[i] * (i - p) + sum[p];
            }

            stack.push(i);

            res += sum[i];
        }

        return res;
    }

    public int mySol_fail(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] mat2 = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat2[i][j] = mat[i][j];

                if (i > 0 && mat2[i][j] > 0) {
                    mat2[i][j] += mat2[i - 1][j];
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (mat[i][j] > 0) {
                    mat[i][j] += mat[i][j - 1];
                }
            }
        }

        int ans = 0;

        for (int[] row : mat) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\n");

        for (int[] row : mat2) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\n");

        int[] prevRow = new int[n];
        int[][] dp = new int[m + 1][n + 1];

        int[] minWidth = new int[n];

        Arrays.fill(minWidth, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            int minHeight = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (mat[i][j] > 0) {
                    minHeight = Math.min(minHeight, mat2[i][j]);

                    int width = mat[i][j];

                    minWidth[j] = Math.min(minWidth[j], width);

                    // ans += count(width, minHeight);
                    // ans += width * minHeight + (mat2[i][j] - minHeight);
                    ans += minWidth[j] * minHeight;

                    System.out.println(String.format("[%d][%d], mw:%d, mh:%d", i, j, minWidth[j], minHeight));
                } else {
                    minHeight = Integer.MAX_VALUE;
                    minWidth[j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }

        return ans;
    }

    private int count(int w, int h) {
        return (w * (1 + w) / 2) * (h * (1 + h) / 2);
    }
}