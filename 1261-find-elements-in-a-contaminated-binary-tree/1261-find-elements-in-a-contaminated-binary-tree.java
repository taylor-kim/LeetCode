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
class FindElements {
    private TreeNode root;

    public FindElements(TreeNode root) {
        recover(root, 0);
        this.root = root;
    }

    private void recover(TreeNode node, int val) {
        if (node == null) return;

        node.val = val;

        recover(node.left, val * 2 + 1);
        recover(node.right, val * 2 + 2);
    }
    
    public boolean find(int target) {
        TreeNode node = root;

        return find(node, target);
    }

    private boolean find(TreeNode node, int target) {
        if (node == null) return false;

        if (node.val == target) return true;

        return find(node.left, target) || find(node.right, target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */