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
        return moreSimple(root);
    }

    public int moreSimple(TreeNode root) {
        return moreSimple(root, 0);
    }

    public int moreSimple(TreeNode root, int num) {
        if (root == null) return 0;

        int next = num * 10 + root.val;

        if (root.left == null && root.right == null) return next;

        return moreSimple(root.left, next) + moreSimple(root.right, next);
    }

    public int mySol(TreeNode root) {
        int[] sum = new int[1];
        
        mySol(root, sum, 0);

        return sum[0];
    }

    public void mySol(TreeNode root, int[] sum, int num) {
        if (root == null) return;

        int next = num * 10 + root.val;

        if (root.left == null && root.right == null) {
            sum[0] += next;
        } else {
            mySol(root.left, sum, next);
            mySol(root.right, sum, next);
        }
    }
}