/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        return mySol(m, n, head);
    }

    int[][] dirs = {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public int[][] mySol(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];

        for (int[] row : ans) Arrays.fill(row, -1);

        int dir = 0;

        int y = 0;
        int x = 0;

        while (head != null) {
            ans[y][x] = head.val;
            head = head.next;

            if (!isOkToGo(y + dirs[dir][0], x + dirs[dir][1], ans)) {
                dir = (dir + 1) % dirs.length;
            }

            y += dirs[dir][0];
            x += dirs[dir][1];
        }

        return ans;
    }

    private boolean isOkToGo(int y, int x, int[][] mat) {
        return y >= 0 && y < mat.length && x >= 0 && x < mat[0].length && mat[y][x] == -1;
    }
}