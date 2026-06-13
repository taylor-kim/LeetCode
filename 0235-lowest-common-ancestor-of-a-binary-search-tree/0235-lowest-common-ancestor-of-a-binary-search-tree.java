/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return try_20260613(root, p, q);
    }

    public TreeNode try_20260613(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            return search(root, root, q, p);
        } else {
            return search(root, root, p, q);
        }
    }

    public TreeNode search(TreeNode parent, TreeNode node, TreeNode p, TreeNode q) {
        if (q.val < node.val) return try_20260613(node.left, p, q);

        if (node.val < p.val) return try_20260613(node.right, p, q);

        return node;
    }
}