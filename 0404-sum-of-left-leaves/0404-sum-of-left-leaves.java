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
    public int sumOfLeftLeaves(TreeNode root) {
        return others_rec(root);
    }

    public int others_rec(TreeNode root) {
        if (root == null) return 0;

        int ans = 0;

        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) ans = root.left.val;
            else ans = others_rec(root.left);
        }

        return ans + others_rec(root.right);
    }

    public int mySol2(TreeNode root) {
        Stack<Pair<TreeNode, Boolean>> stack = new Stack();

        int ans = 0;

        TreeNode node = root;
        boolean isLeft = false;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(new Pair(node, isLeft));

                node = node.left;
                isLeft = true;
            }

            Pair<TreeNode, Boolean> pair = stack.pop();
            node = pair.getKey();
            
            if (node.left == null && node.right == null && pair.getValue()) {
                ans += node.val;
            }

            if (node.right != null) {
                isLeft = false;
                node = node.right;
            } else {
                node = null;
            }
        }

        return ans;
    }

    public int mySol(TreeNode root) {
        return mySol(root, false);
    }

    public int mySol(TreeNode root, boolean isLeft) {
        if (root == null) return 0;

        if (root.left == null && root.right == null && isLeft) {
            return root.val;
        }

        return mySol(root.left, true) + mySol(root.right, false);
    }
}