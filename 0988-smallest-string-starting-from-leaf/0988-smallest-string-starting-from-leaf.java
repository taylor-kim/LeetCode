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
    public String smallestFromLeaf(TreeNode root) {
        return mySol(root);
    }

    String ans = null;

    public String mySol(TreeNode root) {
        mySol_dfs(root, "");

        return ans;
    }

    public void mySol_dfs(TreeNode root, String s) {
        if (root == null) return;

        char c = (char)(root.val + 'a');

        s = c + s;

        if (root.left == null && root.right == null) {
            if (ans == null || ans.compareTo(s) > 0) {
                ans = s;
            }
        }

        mySol_dfs(root.left, s);
        mySol_dfs(root.right, s);
    }
}