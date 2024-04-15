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
    public int sumNumbers(TreeNode root) {
        return mySol_rec(root);
    }

    public int mySol_rec(TreeNode root) {
        return mySol_rec(root, 0);
    }

    public int mySol_rec(TreeNode root, int val) {
        int sum = val * 10 + root.val;

        if (root.left == null && root.right == null) {
            return sum;
        } else if (root.left == null) {
            return mySol_rec(root.right, sum);
        } else if (root.right == null) {
            return mySol_rec(root.left, sum);
        } else {
            return mySol_rec(root.left, sum) + mySol_rec(root.right, sum);
        }
    }
}