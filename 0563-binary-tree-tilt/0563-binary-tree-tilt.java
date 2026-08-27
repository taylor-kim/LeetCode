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
    public int findTilt(TreeNode root) {
        mySol(root);

        return ans;
    }

    int ans = 0;

    public int mySol(TreeNode root) {
        if (root == null) return 0;

        int leftSum = mySol(root.left);
        int rightSum = mySol(root.right);

        ans += Math.abs(leftSum - rightSum);

        return root.val + leftSum + rightSum;
    }
}