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
        return try_20260209(root);
    }

    public TreeNode try_20260209(TreeNode root) {
        List<TreeNode> list = new ArrayList();
        inorder(root, list);
        return makeBalanced(list, 0, list.size() - 1);
    }

    private void inorder_20260209(TreeNode root, List<TreeNode> list) {
        if (root == null) return;

        inorder_20260209(root.left, list);
        list.add(root);
        inorder_20260209(root.right, list);
    }

    private TreeNode makeBalanced(List<TreeNode> list, int lo, int hi) {
        if (lo > hi) return null;

        int mid = lo + (hi - lo) / 2;

        TreeNode node = list.get(mid);

        node.left = makeBalanced(list, lo, mid - 1);
        node.right = makeBalanced(list, mid + 1, hi);

        return node;
    }

    public TreeNode mySol(TreeNode root) {
        List<TreeNode> list = new ArrayList();
        inorder(root, list);

        return buildTree(list, 0, list.size() - 1);
    }

    private void inorder(TreeNode root, List<TreeNode> list) {
        if (root == null) return;

        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
    }

    private TreeNode buildTree(List<TreeNode> list, int lo, int hi) {
        if (lo > hi) return null;

        int mid = lo + (hi - lo) / 2;

        TreeNode root = list.get(mid);

        root.left = buildTree(list, lo, mid - 1);
        root.right = buildTree(list, mid + 1, hi);

        return root;
    }
}