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
    public boolean isBalanced(TreeNode root) {
        return try_others(root);
    }

    public boolean try_others(TreeNode root) {
        return topdown(root) >= 0;
    }

    private int topdown(TreeNode node) {
        if (node == null) return 0;

        int left = topdown(node.left);
        int right = topdown(node.right);

        if (left >= 0 && right >= 0) {
            return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1: -1;
        } else {
            return -1;
        }
    }

    boolean ans = true;

    public boolean mySol(TreeNode root) {
        if (root == null) return true;

        depth(root);

        return ans;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;

        int left = depth(root.left);
        int right = depth(root.right);

        if (Math.abs(left - right) > 1) {
            ans = false;
        }

        return Math.max(left, right) + 1;
    }
}