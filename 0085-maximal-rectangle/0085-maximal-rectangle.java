class Solution {
    public int maximalRectangle(char[][] matrix) {
        return try_20260131_after_read(matrix);
    }

    /**
    [
        ["0","0","1","0"]
        ["0","0","1","0"]
        ["0","0","1","0"]
        ["0","0","1","1"]
        ["0","1","1","1"]
        ["0","1","1","1"]
        ["1","1","1","1"]
    ]
        1,3,7,4
     */

    public int try_20260131_after_read(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] hights = new int[n];

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    hights[j] = 0;
                } else {
                    hights[j]++;
                }
            }

            Stack<Integer> stack = new Stack();

            for (int j = 0; j <= n; j++) {
                while (!stack.isEmpty() && (j == n || hights[stack.peek()] >= hights[j])) {
                    int h = hights[stack.pop()];
                    int left = stack.isEmpty() ? -1 : stack.peek();

                    ans = Math.max(ans, h * (j - left - 1));
                }

                stack.push(j);
            }
        }

        return ans;
    }

    public int try_20260131_fail(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] hights = new int[n];

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    hights[j] = 0;
                } else {
                    hights[j]++;
                }
            }

            Stack<Integer> stack = new Stack();

            Stack<Integer> incStack = new Stack();

            for (int j = 0; j < n; j++) {
                if (hights[j] == 0) {
                    while (!stack.isEmpty()) {
                        int width = stack.size();

                        ans = Math.max(ans, width * hights[stack.pop()]);
                    }

                    int width = 1;

                    while (!incStack.isEmpty()) {
                        ans = Math.max(ans, width++ * (hights[incStack.pop()]));
                    }

                    continue;
                }

                if (!stack.isEmpty() && hights[stack.peek()] < hights[j]) {
                    ans = Math.max(ans, hights[stack.peek()] * (stack.size() + 1));

                    while (!stack.isEmpty()) {
                        int width = stack.size();

                        ans = Math.max(ans, width * hights[stack.pop()]);
                    }
                }

                if (!incStack.isEmpty() && hights[incStack.peek()] >= hights[j]) {
                    while (!incStack.isEmpty()) {
                        int left = incStack.pop();

                        ans = Math.max(ans, (j - left + 1) * hights[j]);
                    }
                }

                stack.push(j);
                incStack.push(j);
            }

            while (!stack.isEmpty()) {
                int width = stack.size();

                ans = Math.max(ans, width * hights[stack.pop()]);
            }

            int width = 1;

            // while (!incStack.isEmpty()) {
            //     ans = Math.max(ans, width * hights[incStack.pop()]);
            // }
        }

        return ans;
    }
}