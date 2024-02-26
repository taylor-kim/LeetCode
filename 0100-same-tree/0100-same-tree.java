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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return mySol(p, q);
    }
    
    public boolean mySol(TreeNode p, TreeNode q) {
        if (p != null && q != null) {
            return p.val == q.val && mySol(p.left, q.left) && mySol(p.right, q.right);
        } else if (p == null && q == null) {
            return true;
        } else {
            return false;
        }
    }
}