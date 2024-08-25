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
    public List<Integer> postorderTraversal(TreeNode root) {
        return mySol_rec(root);
    }

    public List<Integer> mySol_rec(TreeNode root) {
        List<Integer> list = new ArrayList();

        mySol_rec(root, list);

        return list;
    }

    private void mySol_rec(TreeNode root, List<Integer> list) {
        if (root == null) return;

        mySol_rec(root.left, list);
        mySol_rec(root.right, list);

        list.add(root.val);
    }
}