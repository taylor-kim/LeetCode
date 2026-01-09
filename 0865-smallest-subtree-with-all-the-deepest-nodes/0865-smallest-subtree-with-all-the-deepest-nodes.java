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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return mySol(root);
    }

    int max = 0;
    TreeNode ans = null;

    public TreeNode mySol(TreeNode root) {
        dfs(root, 0);

        return ans;
    }

    public int dfs(TreeNode root, int depth) {
        if (root == null) return depth;

        int ld = dfs(root.left, depth + 1);
        int rd = dfs(root.right, depth + 1);

        if (ld == rd) {
            if (max <= ld) {
                max = ld;
                ans = root;
            }
        } else if (ld > rd) {
            if (max < ld) {
                max = ld;
                ans = root.left;
            }
        } else {
            if (max < rd) {
                max = rd;
                ans = root.right;
            }
        }

        return Math.max(ld, rd);
    }
}