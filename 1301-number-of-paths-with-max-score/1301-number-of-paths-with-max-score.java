class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        return mySol2(board);
    }

    public int[] mySol2(List<String> board) {
        int m = board.size();
        int n = board.get(0).length();
        int[][][] costs = new int[m + 1][n + 1][2];

        for (int i = 0; i <= m; i++) {
            costs[i][n][0] = -1;
        }

        for (int j = 0; j <= n; j++) {
            costs[m][j][0] = -1;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char c = board.get(i).charAt(j);

                if (c == 'X') {
                    costs[i][j][0] = -1;
                    continue;
                }

                int num = (c == 'S' || c == 'E') ? 0 : (c - '0');

                costs[i][j][0] = num;
                costs[i][j][1] = 1;

                int[] maxCount = getMax(costs[i][j + 1], costs[i + 1][j]);
                maxCount = getMax(maxCount, costs[i + 1][j + 1]);

                if (maxCount[0] >= 0) {
                    costs[i][j][0] = maxCount[0] + num;
                    costs[i][j][1] = maxCount[1];
                } else if (maxCount[0] < 0 && c != 'S') {
                    costs[i][j][0] = -1;
                }

                // System.out.println("i:%d, j:%d num:%d - sum:%d, count:%d".formatted(i, j, num, costs[i][j][0], costs[i][j][1]));
            }
        }

        return costs[0][0][0] >= 0 ? costs[0][0] : new int[] {0, 0};
    }

    private int[] getMax(int[] a, int[] b) {
        if (a[0] == b[0]) {
            int mod = (int)1e9 + 7;
            return new int[] {a[0], (a[1] + b[1]) % mod};
        } else if (a[0] < b[0]) {
            return b;
        } else {
            return a;
        }
    }

    public int[] mySol_tle(List<String> board) {
        int[] ans = new int[] {0, 0};

        int m = board.size();
        int n = board.get(0).length();

        int[][] costs = new int[m][n];

        for (int[] row : costs) {
            Arrays.fill(row, -1);
        }

        topdown(board, m - 1, n - 1, 0, costs, ans);

        return ans;
    }

    private void topdown(List<String> board, int r, int c, int sum, int[][] costs, int[] ans) {
        if (r < 0 || c < 0 || board.get(r).charAt(c) == 'X') return;

        if (r == 0 && c == 0) {
            if (sum > ans[0]) {
                ans[0] = sum;
                ans[1] = 1;
            } else if (sum == ans[0]) {
                int mod = (int)1e9 + 7;
                ans[1] = (ans[1] + 1) % mod;
            }
        }

        char ch = board.get(r).charAt(c);

        int num = ch == 'S' ? 0 : (ch - '0');

        int next = sum + num;

        if (costs[r][c] > next) return;

        costs[r][c] = next;

        topdown(board, r - 1, c, next, costs, ans);
        topdown(board, r, c - 1, next, costs, ans);
        topdown(board, r - 1, c - 1, next, costs, ans);
    }
}