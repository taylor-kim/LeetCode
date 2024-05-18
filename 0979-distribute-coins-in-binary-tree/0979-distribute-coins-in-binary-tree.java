/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int distributeCoins(TreeNode root) {
        official(root);
        return moves;
    }

    int moves = 0;

    public int official(TreeNode root) {
        if (root == null) return 0;

        int left = official(root.left);
        int right = official(root.right);

        moves += Math.abs(left) + Math.abs(right);

        return root.val - 1 + left + right;
    }

    public int mySol_fail(TreeNode root) {
        return mySol(root, 0);
    }

    public int mySol(TreeNode root, int delta) {
        if (root == null) return delta;

        if (delta == 0 && root.val == 1) {
            return mySol(root.left, 0) + mySol(root.right, 0);
        }

        if (root.left == null && root.right == null) {
            
        }

        delta += root.val - 1;

        int left = delta / 2;
        int right = delta / 2;
        int odd = delta % 2;

        return -1;
    }
}