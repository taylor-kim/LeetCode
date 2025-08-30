class Solution {
    public boolean isValidSudoku(char[][] board) {
        return mySol(board);
    }

    public boolean mySol(char[][] board) {
        int n = 9;

        int[] rows = new int[n];
        int[] cols = new int[n];
        int[] boxes = new int[n];

        List<int[]> targets = new ArrayList();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    int digit = board[i][j] - '0';

                    if (!isAbsent(digit, rows[i])) return false;
                    if (!isAbsent(digit, cols[j])) return false;
                    if (!isAbsent(digit, boxes[(i / 3) * 3 + (j / 3)])) return false;

                    setDigit(digit, rows, i);
                    setDigit(digit, cols, j);
                    setDigit(digit, boxes, (i / 3) * 3 + (j / 3));
                }
            }
        }

        return true;
    }

    public boolean topdown(List<int[]> targets, int index, int[] rows, int[] cols, int[] boxes) {
        if (isValid(rows, cols, boxes)) return true;

        if (index >= targets.size()) return false;

        int r = targets.get(index)[0];
        int c = targets.get(index)[1];

        for (int digit = 1; digit < 10; digit++) {
            int boxRow = r / 3;
            int boxCol = c / 3;
            int boxIndex = boxRow * 3 + boxCol;

            if (isAbsent(digit, rows[r], cols[c], boxes[boxIndex])) {
                setDigit(digit, rows, r);
                setDigit(digit, cols, c);
                setDigit(digit, boxes, boxIndex);

                if (topdown(targets, index + 1, rows, cols, boxes)) return true;

                unsetDigit(digit, rows, r);
                unsetDigit(digit, cols, c);
                unsetDigit(digit, boxes, boxIndex);
            }
        }

        return false;
    }

    private boolean isValid(int[] rows, int[] cols, int[] boxes) {
        return isValid(rows) && isValid(cols) && isValid(boxes);
    }

    private boolean isValid(int[] datas) {
        int full = (1 << 9) - 1;

        for (int d : datas) {
            if (d != full) return false;
        }

        return true;
    }

    private boolean isAbsent(int digit, int row, int col, int box) {
        return isAbsent(digit, row) && isAbsent(digit, col) && isAbsent(digit, box);
    }

    private boolean isAbsent(int digit, int data) {
        return (data & (1 << (digit - 1))) == 0;
    }

    private void setDigit(int digit, int[] datas, int index) {
        datas[index] = datas[index] | (1 << (digit - 1));
    }

    private void unsetDigit(int digit, int[] datas, int index) {
        datas[index] = datas[index] & (~(1 << (digit - 1)));
    }
}