class Solution {
    public int snakesAndLadders(int[][] board) {
        return mySol(board);
    }

    public int mySol(int[][] board) {
        int n = board.length;

        int roll = 1;
        int max = n * n;

        Queue<Integer> queue = new LinkedList();
        queue.add(1);

        boolean[] visit = new boolean[max + 1];
        visit[1] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int label = queue.poll();

                for (int next = label + 1; next <= Math.min(label + 6, max); next++) {
                    int logicalRow = (next - 1) / n;

                    int row = n - logicalRow - 1;

                    boolean isReversed = logicalRow % 2 == 1;

                    int col = isReversed ? n - ((next - 1) % n) - 1 : (next - 1) % n;

                    // System.out.println(String.format("label:%d, next:%d, logicalRow:%d, row:%d, col:%d"
                    //                                 , label, next, logicalRow, row, col));

                    int target = board[row][col] != -1 ? board[row][col] : next;

                    if (target == max) return roll;

                    if (!visit[target]) {
                        queue.add(target);
                        visit[target] = true;
                    }
                }
            }

            roll++;
        }

        return -1;
    }
}