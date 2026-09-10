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
    public int averageOfSubtree(TreeNode root) {
        return mySol(root);
    }

    public int mySol(TreeNode root) {
        int[] data = new int[3];
        dfs(root, data);
        return data[0];
    }

    public void dfs(TreeNode root, int[] data) {
        if (root == null) return;

        dfs(root.left, data);

        int lSum = data[1];
        int lCount = data[2];

        data[1] = 0;
        data[2] = 0;

        dfs(root.right, data);

        data[1] += lSum;
        data[2] += lCount;

        data[1] += root.val;
        data[2]++;

        if (data[1] / data[2] == root.val) data[0]++;
    }
}