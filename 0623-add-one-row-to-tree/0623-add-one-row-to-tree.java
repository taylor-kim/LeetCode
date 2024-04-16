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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        return mySol(root, val, depth);
    }

    public TreeNode mySol(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        } else {
            mySol_rec(root, val, depth - 1);

            return root;
        }
    }

    public void mySol_rec(TreeNode root, int val, int depth) {
        if (root == null) return;

        if (depth == 1) {
            TreeNode newLeft = new TreeNode(val, root.left, null);
            TreeNode newRight = new TreeNode(val, null, root.right);
            root.left = newLeft;
            root.right = newRight;
        } else {
            mySol_rec(root.left, val, depth - 1);
            mySol_rec(root.right, val, depth - 1);
        }
    }
}