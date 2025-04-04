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
        Result result = mySol(null, root, 0);

        return result.ans;
    }

    public Result mySol(TreeNode parent, TreeNode root, int depth) {
        if (root == null) {
            return new Result(parent, depth - 1);
        }

        Result left = mySol(root, root.left, depth + 1);
        Result right = mySol(root, root.right, depth + 1);

        if (left.depth == right.depth) {
            return new Result(root, left.depth);
        } else if (left.depth > right.depth) {
            return left;
        } else {
            return right;
        }
    }

    class Result {
        TreeNode ans;
        int depth;

        Result(TreeNode ans, int depth) {
            this.ans = ans;
            this.depth = depth;
        }
    }
}