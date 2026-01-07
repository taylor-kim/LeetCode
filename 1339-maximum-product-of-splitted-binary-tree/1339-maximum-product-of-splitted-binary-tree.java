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
    public int maxProduct(TreeNode root) {
        return mySol(root);
    }

    public int mySol(TreeNode root) {
        long totalSum = sum(root);

        long[] max = {0};

        dfs(root, totalSum, max);

        int mod = (int)1e9 + 7;

        return (int)(max[0] % mod);
    }

    public long dfs(TreeNode root, long totalSum, long[] max) {
        if (root == null) return 0;

        long leftSum = dfs(root.left, totalSum, max);
        long rightSum = dfs(root.right, totalSum, max);
        long sum = root.val + leftSum + rightSum;

        long cutParent = (totalSum - sum) * sum;
        
        max[0] = Math.max(max[0], cutParent);

        return sum;
    }

    public long sum(TreeNode root) {
        if (root == null) return 0;

        long leftSum = sum(root.left);
        long rightSum = sum(root.right);

        return root.val + leftSum + rightSum;
    }
}