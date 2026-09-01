class Solution {
    public void gameOfLife(int[][] board) {
        mySol(board);
    }

    public void mySol(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] ans = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = getLives(board, i, j);

                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    ans[i][j] = 1;
                } else if (board[i][j] == 0 && lives == 3) {
                    ans[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ans[i][j];
            }
        }
    }

    private int getLives(int[][] board, int y, int x) {
        int m = board.length;
        int n = board[0].length;
        int lives = 0;

        for (int i = Math.max(0, y - 1); i <= Math.min(y + 1, board.length - 1); i++) {
            for (int j = Math.max(0, x - 1); j <= Math.min(x + 1, board[0].length - 1); j++) {
                if (i == y && j == x) continue;

                lives += board[i][j];
            }
        }

        return lives;
    }
}