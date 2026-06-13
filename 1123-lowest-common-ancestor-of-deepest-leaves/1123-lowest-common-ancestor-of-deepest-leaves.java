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
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return try_20260613(root);
    }

    public TreeNode try_20260613(TreeNode root) {
        return search(root, root, 0).node;
    }

    public TreeWithDepth search(TreeNode parent, TreeNode node, int depth) {
        if (node == null) return new TreeWithDepth(parent, depth);

        TreeWithDepth left = search(node, node.left, depth + 1);
        TreeWithDepth right = search(node, node.right, depth + 1);

        if (left.depth > right.depth) {
            return left;
        } else if (left.depth < right.depth) {
            return right;
        } else {
            return new TreeWithDepth(node, left.depth);
        }
    }

    public class TreeWithDepth {
        TreeNode node;
        int depth;

        public TreeWithDepth(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}