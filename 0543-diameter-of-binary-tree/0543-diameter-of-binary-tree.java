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
    public int diameterOfBinaryTree(TreeNode root) {
        mySol(root);
        
        return ans;
    }
    
    int ans = 0;
    
    public int mySol(TreeNode root) {
        if (root == null) return -1;
        
        int left = mySol(root.left) + 1;
        int right = mySol(root.right) + 1;
        
        int sum = left + right;
        
        // System.out.println(String.format("val:%d, sum:%d", root.val, sum));
        
        ans = Math.max(ans, sum);
        
        return Math.max(left, right);
    }
    
    private int checkDiameter(TreeNode node) {
        if (node == null) return 0;
        
        int left = 0;
        int right = 0;
        
        if (node.left != null) {
            left = 1 + checkDiameter(node.left);
        }
        
        if (node.right != null) {
            right = 1 + checkDiameter(node.right);
        }
        
        return left + right;
    }
}