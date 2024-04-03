class Solution {
    public boolean exist(char[][] board, String word) {
        return mySol(board, word);
    }

    public boolean mySol(char[][] board, String word) {
        boolean[][] visit = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (mySol(board, word, i, j, 0, visit)) return true;
            }
        }

        return false;
    }

    public boolean mySol(char[][] board, String word, int y, int x, int index, boolean[][] visit) {
        // System.out.println(String.format("y:%d, x:%d, index:%d, length:%d", y, x, index, word.length()));

        if (index >= word.length()) return true;

        if (y >= board.length || y < 0 || x >= board[0].length || x < 0) return false;

        if (visit[y][x] || board[y][x] != word.charAt(index)) return false;

        visit[y][x] = true;

        for (int delta = -1; delta <= 1; delta += 2) {
            if (mySol(board, word, y + delta, x, index + 1, visit)) return true;
        }

        for (int delta = -1; delta <= 1; delta += 2) {
            if (mySol(board, word, y, x + delta, index + 1, visit)) return true;
        }

        visit[y][x] = false;

        return false;
    }
}