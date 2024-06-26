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
    public TreeNode balanceBST(TreeNode root) {
        return mySol(root);
    }

    public TreeNode mySol(TreeNode root) {
        List<Integer> list = new ArrayList();

        inorder(root, list);

        return make(list, 0, list.size() - 1);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    private TreeNode make(List<Integer> list, int lo, int hi) {
        if (lo > hi) return null;

        int mid = lo + (hi - lo) / 2;

        return new TreeNode(
            list.get(mid)
            , make(list, lo, mid - 1)
            , make(list, mid + 1, hi)
        );
    }
}