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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return mySol(root, target);
    }

    public TreeNode mySol(TreeNode root, int target) {
        if (root == null) return null;

        if (root.left == null && root.right == null && root.val == target) {
            return null;
        } else {
            root.left = mySol(root.left, target);
            root.right = mySol(root.right, target);

            if (root.left == null && root.right == null && root.val == target) {
                return null;
            } else {
                return root;
            }
        }
    }
}