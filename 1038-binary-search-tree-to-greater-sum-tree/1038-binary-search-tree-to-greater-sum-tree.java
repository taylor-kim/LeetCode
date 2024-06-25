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
    public TreeNode bstToGst(TreeNode root) {
        return mySol(root);
    }

    int sum = 0;

    public TreeNode mySol(TreeNode root) {
        if (root == null) return null;

        TreeNode right = mySol(root.right);

        sum += root.val;
        root.val = sum;

        mySol(root.left);

        return root;
    }
}